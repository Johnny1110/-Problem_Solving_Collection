package com.frizo.schedule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class QuartzTaskManager extends BaseQuartzJob {

  public static final String CRON_EX = "0 0/1 * * * ?";

  public static final String TASK_MANAGER_GROUP = "MANAGERJOB";

  public static final String SUB_JOB_GROUP = "SUBJOB";

  private final ScheduleSettingService scheduleSettingService;

  private final ScheduleTool scheduleTool;

  private final ContainerInfo containerInfo;

  private Thread thread;

  public QuartzTaskManager(
      ScheduleSettingService scheduleSettingService,
      ScheduleTool scheduleTool,
      ContainerInfo containerInfo) {
    this.scheduleSettingService = scheduleSettingService;
    this.scheduleTool = scheduleTool;
    this.containerInfo = containerInfo;
  }

  @SneakyThrows
  @Override
  public void execute(JobExecutionContext context) {
    this.thread = Thread.currentThread();
    Scheduler scheduler = context.getScheduler();
    log.debug("[QuartzTaskManager] run()");
    List<ScheduleSettingData> allScheduleSettingDataList =
        scheduleSettingService.findAllScheduleSettingData(); // 找出所有 schedules

    Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(SUB_JOB_GROUP));
    for (JobKey jobKey : jobKeys) {
      // 檢查是否仍具有效用
      boolean isEffective = scheduleTool.checkIsEffective(jobKey.getName());
      if (!isEffective) { // 過期就刪掉 container 的 job
        scheduler.deleteJob(jobKey);
      }
    }

    Map<String, JobKey> nameKeyPair =
        scheduler.getJobKeys(GroupMatcher.jobGroupEquals(SUB_JOB_GROUP)).stream()
            .collect(Collectors.toMap(JobKey::getName, Function.identity()));
    for (ScheduleSettingData scheduleSettingData : allScheduleSettingDataList) {
      String dbJobName = String.valueOf(scheduleSettingData.getScheduleSettingId());
      Date dbLastUpdateTime = scheduleSettingData.getLastUpdateTime();
      Boolean enable = scheduleSettingData.getEnable();
      if (nameKeyPair.containsKey(dbJobName)) { // 如果 dbJobName 已經被本容器註冊過
        JobKey jobKey = nameKeyPair.get(dbJobName);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        Date jobLastUpdateTime = (Date) jobDetail.getJobDataMap().get("lastUpdateTime");
        if (!enable || !jobLastUpdateTime.equals(dbLastUpdateTime)) {
          // 如果 db 關閉，或者有設定更新 -> 刪除 job
          log.debug("發現一個被關閉 or 更新設定的排程，id:[{}]", dbJobName);
          boolean deleteResult = deleteJob(scheduler, jobKey);
          log.debug("[deleteResult]: {}", deleteResult);
        }
      }
    }

      List<ScheduleSettingData> enableScheduleSettingDataList =
              allScheduleSettingDataList.stream()
                      .filter(ScheduleSettingData::getEnable)
                      .collect(Collectors.toList());
      for (ScheduleSettingData scheduleSettingData : enableScheduleSettingDataList) {
          //檢查容器註冊的 Job 數量是否超過上限
          boolean isReachedLimit = isReachedContainerJobQtyLimit(scheduler, enableScheduleSettingDataList.size());
          if(isReachedLimit){
              log.debug("container:{} 以達到註冊Job數量上限，無法繼續註冊。", containerInfo.getUuid());
              break;
          }

          JobDataMap jobDataMap = new JobDataMap();
          jobDataMap.put("lastUpdateTime", scheduleSettingData.getLastUpdateTime());
          jobDataMap.put("scheduleSettingData", scheduleSettingData);

          // 定義JobDetail
          JobDetail jobDetail =
                  JobBuilder.newJob(DynamicJob.class)
                          .withIdentity(
                                  String.valueOf(scheduleSettingData.getScheduleSettingId()), SUB_JOB_GROUP)
                          .usingJobData(jobDataMap)
                          .build();

          // 定義Trigger，使用Cron表達式
          Trigger trigger =
                  TriggerBuilder.newTrigger()
                          .withIdentity(String.valueOf(scheduleSettingData.getScheduleSettingId()))
                          .startNow()
                          .withSchedule(CronScheduleBuilder.cronSchedule(scheduleSettingData.getTriggerTime()))
                          .build();

          // 將JobDetail和Trigger安排到Scheduler中
          boolean scheduleResult = scheduleJob(scheduler, jobDetail, trigger);
          log.debug("[scheduleResult]: {}", scheduleResult);
      }

  }

  private boolean isReachedContainerJobQtyLimit(Scheduler scheduler, int enabledJobQty)
      throws SchedulerException {
    int containerQty = containerInfo.getContainerQty();
    Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(SUB_JOB_GROUP));
    int registedJobQty = jobKeys.size();
    int avgQty = (int) Math.ceil((double) enabledJobQty / containerQty);
    return avgQty <= registedJobQty;
  }

  private boolean scheduleJob(Scheduler scheduler, JobDetail jobDetail, Trigger trigger)
      throws SchedulerException {
    JobDataMap jobDataMap = jobDetail.getJobDataMap();
    ScheduleSettingData scheduleSettingData =
        (ScheduleSettingData) jobDataMap.get("scheduleSettingData");
    // 搶佔 redis lock
    boolean result =
        scheduleTool.scheduleJob(String.valueOf(scheduleSettingData.getScheduleSettingId()));
    if (result) {
      // 成功之後註冊進 container
      scheduler.scheduleJob(jobDetail, trigger);
      // Thread.sleep(500L);
    }
    return result;
  }

  private boolean deleteJob(Scheduler scheduler, JobKey jobKey) throws SchedulerException {
    // 刪除 job & redisKey
    return scheduler.deleteJob(jobKey) && scheduleTool.deleteJob(jobKey.getName());
  }

  @Override
  public Thread getCurrentThread() {
    return this.thread;
  }
}
