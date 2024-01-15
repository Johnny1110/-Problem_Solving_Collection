package com.frizo.schedule;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/** Spring 啟動後執行 onApplicationEvent 註冊 QuartzTaskManager */
@Component
@Slf4j
public class ApplicationScheduleCore {

  private final Scheduler scheduler;

  private final ScheduleTool scheduleTool;

  private final ContainerInfo containerInfo;

  private final Environment env;

  private static boolean JOB_MANAGER_SWITCH = false;

  public ApplicationScheduleCore(
      Scheduler scheduler,
      ScheduleTool scheduleTool,
      ContainerInfo containerInfo,
      Environment env) {
    this.scheduler = scheduler;
    this.scheduleTool = scheduleTool;
    this.containerInfo = containerInfo;
    this.env = env;
  }

  @SneakyThrows
  public void startup() {
    if (JOB_MANAGER_SWITCH) {
      throw new RuntimeException("QuartzTaskManager already startup.");
    } else {
      // 定義JobDetail
      JobDetail jobDetail =
          JobBuilder.newJob(QuartzTaskManager.class)
              .withIdentity("QuartzTaskManager", QuartzTaskManager.TASK_MANAGER_GROUP)
              .build();

      // 定義Trigger，使用Cron表達式
      Trigger trigger =
          TriggerBuilder.newTrigger()
              .startNow()
              .withSchedule(CronScheduleBuilder.cronSchedule(QuartzTaskManager.CRON_EX))
              .build();

      scheduler.scheduleJob(jobDetail, trigger);
      JOB_MANAGER_SWITCH = true;
      log.debug("初始化，完成 QuartzTaskManager 排程註冊行為。");
    }
  }

  /** 關閉所有排程（包括 taskManager） */
  public void shutdown() {
    try {
      List<String> jobNames =
          scheduler
              .getJobKeys(GroupMatcher.jobGroupEquals(QuartzTaskManager.SUB_JOB_GROUP))
              .stream()
              .map(JobKey::getName)
              .collect(Collectors.toList());
      scheduler.shutdown();
      log.debug("scheduler 已經關閉，不再執行新的任務。");

      // 關閉 containerInfo 的 active flag
      containerInfo.disActive();
      log.debug("關閉 containerInfo 的 active flag。");

      log.debug("正在移除 Redis 註冊的排程，jobNames:{}。", jobNames);
      scheduleTool.deleteJobs(jobNames);
      log.debug("完成移除 Redis 註冊的排程。");

      JOB_MANAGER_SWITCH = false;

      // 等待
      int holdingSec =
          Integer.parseInt(
              Objects.requireNonNull(env.getProperty("accounting.shutdown_holding_time")));
      for (int i = 0; i < holdingSec; i++) {
        if (scheduler.getCurrentlyExecutingJobs().size() == 0) {
          break;
        } else {
          Thread.sleep(1000L);
        }
      }

      // 取得所有當前正在執行的任務的 JobExecutionContext
      List<JobExecutionContext> currentlyExecutingJobs = scheduler.getCurrentlyExecutingJobs();
      // 檢查任務是否仍在執行，如果是，則強制關閉
      for (JobExecutionContext jobExecutionContext : currentlyExecutingJobs) {
        BaseQuartzJob job = (BaseQuartzJob) jobExecutionContext.getJobInstance();
        Thread thread = job.getCurrentThread();
        if (thread != null && thread.isAlive()) {
          log.debug(
              "[shutdown] 強制關閉 thread:{} :{}",
              thread.getName(),
              jobExecutionContext.getJobDetail().getKey());
          thread.stop(); // 強制關閉
        }
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<String> getRegistedJobNames() {
    try {
      Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
      return jobKeys.stream().map(JobKey::getName).collect(Collectors.toList());
    } catch (SchedulerException e) {
      throw new RuntimeException(e);
    }
  }

  public List<String> getCurrentlyExecutingJobs() {
    try {
      List<JobExecutionContext> jobExecutionContexts = scheduler.getCurrentlyExecutingJobs();
      return jobExecutionContexts.stream()
          .map(JobExecutionContext::getJobDetail)
          .map(JobDetail::getKey)
          .map(JobKey::getName)
          .collect(Collectors.toList());
    } catch (SchedulerException e) {
      throw new RuntimeException(e);
    }
  }

  public Object getAllRedisRegistedJobNames() {
    return scheduleTool.getAllRedisRegistedJobNames();
  }
}
