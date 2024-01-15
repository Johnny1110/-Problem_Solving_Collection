package com.frizo.schedule;

import com.frizo.service.dynmicEvent.IScheduleEvent;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class DynamicJob extends BaseQuartzJob{

    private Date lastExecuteTime;

    @Autowired
    private ScheduleTool scheduleTool;

    private volatile Thread thread;

    /**
     * 更改 db last_executed_date，新增 record
     *
     * @param scheduleSettingData
     */
    private void executeTaskPreprocess(ScheduleSettingData scheduleSettingData) {
        this.lastExecuteTime = scheduleSettingData.getEventStartTime();
        scheduleTool.updateLastExecutedDate(scheduleSettingData, lastExecuteTime);
    }



    /**
     * 更新 Record，有錯就寄信
     *
     * @param eventStartTime 活動開始時間
     * @param scheduleSettingData 日程設置數據
     * @param eventResult 活動結果
     */
    private void executeTaskPostprocess(
            Date eventStartTime, ScheduleSettingData scheduleSettingData, EventResult eventResult) {
        if (eventResult == null) {
            eventResult = new EventResult();
            eventResult.setResult(false);
            eventResult.addExceptionMsg("排程被強制關閉");
        }
        scheduleTool.createScheduleExecuteRecord(eventStartTime, scheduleSettingData, eventResult);
        if (!eventResult.getResult()) {
            scheduleTool.sendAlarmEmail(scheduleSettingData, eventResult);
        }
    }

    @Override
    public Thread getCurrentThread() {
        return this.thread;
    }

    @Override
    public void execute(JobExecutionContext context) {
        Date eventStartTime = new Date();
        this.thread = Thread.currentThread();

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        ScheduleSettingData scheduleSettingData =
                (ScheduleSettingData) dataMap.get("scheduleSettingData");
        scheduleSettingData.setEventStartTime(eventStartTime);
        executeTaskPreprocess(scheduleSettingData);
        log.debug("[ExchangeRateJob] execute, scheduleSettingData:{}", scheduleSettingData);
        EventResult eventResult = null;

        try {
            String eventMethodName = scheduleSettingData.getEventMethod();
            IScheduleEvent scheduleEventMethod = scheduleTool.getScheduleEventMethod(eventMethodName);
            if (scheduleEventMethod != null) {
                eventResult = scheduleEventMethod.execute(scheduleSettingData);
            } else {
                throw new RuntimeException(
                        String.format(
                                "[ExchangeRateGatewayCronTask] scheduleTool.getScheduleEventMethod(%s) return null.",
                                eventMethodName));
            }
        } catch (Exception ex) {
            log.error(
                    "[ExchangeRateGatewayCronTask] execute failed, scheduleSettingData:{}, detail:{}",
                    scheduleSettingData,
                    ex.getMessage());
            eventResult = new EventResult();
            eventResult.setResult(false);
            eventResult.addExceptionMsg(
                    String.format(
                            "[ExchangeRateGatewayCronTask] execute failed, scheduleSettingData:%s, detail:%s",
                            scheduleSettingData, ex.getMessage()));
        } finally {
            // event 執行完成後執行善後
            executeTaskPostprocess(eventStartTime, scheduleSettingData, eventResult);
        }
    }
}
