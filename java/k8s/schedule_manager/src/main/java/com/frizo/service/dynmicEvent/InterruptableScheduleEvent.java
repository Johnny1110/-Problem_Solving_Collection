package com.frizo.service.dynmicEvent;

import com.frizo.common.BeanUtils;
import com.frizo.schedule.ContainerInfo;
import com.frizo.schedule.EventResult;
import com.frizo.schedule.ScheduleSettingData;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;



@Slf4j
public abstract class InterruptableScheduleEvent implements IScheduleEvent {

  protected final ContainerInfo containerInfo;

  protected ConcurrentHashMap<String, Date> jobInstanceIdStartDatePair;

  protected InterruptableScheduleEvent(ContainerInfo containerInfo) {
    this.containerInfo = containerInfo;
    this.jobInstanceIdStartDatePair = new ConcurrentHashMap<>();
  }

  /**
   * 是否 timeout 與 是否被中斷一起判斷
   *
   * @param scheduleSettingData 排程 timeout_limit_sec 與 scheduleInstanceId
   * @return
   */
  protected boolean isInterrupt(ScheduleSettingData scheduleSettingData) {
    if (scheduleSettingData == null || scheduleSettingData.getScheduleInstanceId() == null) {
      return isInterrupt();
    } else {
      Date startTime = jobInstanceIdStartDatePair.get(scheduleSettingData.getScheduleInstanceId());
      if (startTime == null) {
        return isInterrupt();
      }
      int timeoutLimitSec = scheduleSettingData.getTimeoutLimitSec();
      long diff = new Date().getTime() - startTime.getTime();
      return (diff > timeoutLimitSec * 1000L) || isInterrupt();
    }
  }

  /**
   * 只判斷是否被中斷
   *
   * @return
   */
  protected boolean isInterrupt() {
    return !containerInfo.getActive().get();
  }

  @Override
  public EventResult execute(ScheduleSettingData scheduleSettingData) {
    ScheduleSettingData copyData = BeanUtils.copyBean(scheduleSettingData);
    UUID uuid = UUID.randomUUID();
    String uuidAsString = uuid.toString();
    copyData.setScheduleInstanceId(uuidAsString);
    jobInstanceIdStartDatePair.put(uuidAsString, new Date());
    EventResult result = run(copyData);
    jobInstanceIdStartDatePair.remove(uuidAsString);
    return result;
  }

  public abstract EventResult run(ScheduleSettingData scheduleSettingData);
}
