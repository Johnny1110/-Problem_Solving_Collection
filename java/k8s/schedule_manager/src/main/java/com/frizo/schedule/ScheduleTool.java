package com.frizo.schedule;

import com.frizo.component.RedisUtils;
import com.frizo.service.dynmicEvent.IScheduleEvent;
import com.frizo.service.dynmicEvent.ScheduleMethodAdaptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ScheduleTool {

  private final RedisUtils redisUtils;

  private final RedisTemplate redisTemplate;

  private static final String SCHEDULE_REDIS_CATALOGUE_PREFIX = "frizo:schedule";

  private final RedisScript<Boolean> scheduleRegistScript;

  private final ScheduleMethodAdaptor scheduleMethodAdaptor;

  private final ContainerInfo containerInfo;

  public ScheduleTool(
      RedisUtils redisUtils,
      RedisTemplate redisTemplate,
      @Qualifier("scheduleRegistScript") RedisScript<Boolean> scheduleRegistScript,
      ScheduleMethodAdaptor scheduleMethodAdaptor,
      ContainerInfo containerInfo) {
    this.redisUtils = redisUtils;
    this.redisTemplate = redisTemplate;
    this.scheduleRegistScript = scheduleRegistScript;
    this.scheduleMethodAdaptor = scheduleMethodAdaptor;
    this.containerInfo = containerInfo;
  }

  public IScheduleEvent getScheduleEventMethod(String eventMethod) {

    Optional<IScheduleEvent> optional = scheduleMethodAdaptor.getScheduleEventMethod(eventMethod);
    if (optional.isEmpty()) {
      throw new RuntimeException(
          String.format(
              "[getScheduleEventMethod] input eventMethod:%s can't match any IScheduleEvent.",
              eventMethod));
    } else {
      return optional.get();
    }
  }


  public boolean deleteJob(String taskName) {
    String redisKey = SCHEDULE_REDIS_CATALOGUE_PREFIX + ":" + taskName;
    return redisUtils.remove(redisKey);
  }

  public void deleteJobs(List<String> jobNames) {
    jobNames =
        jobNames.stream()
            .map(jobName -> SCHEDULE_REDIS_CATALOGUE_PREFIX + ":" + jobName)
            .collect(Collectors.toList());
    redisTemplate.delete(jobNames);
  }

  public boolean scheduleJob(String taskName) {
    String redisKey = SCHEDULE_REDIS_CATALOGUE_PREFIX + ":" + taskName;
    String containerUUID = containerInfo.getUuid();
    boolean result =
        (boolean) redisTemplate.execute(scheduleRegistScript, List.of(redisKey), containerUUID);
    log.info("Task {} is already register : {}", taskName, result);
    return result;
  }

  public Object getAllRedisRegistedJobNames() {
    String redisKey = SCHEDULE_REDIS_CATALOGUE_PREFIX + ":" + "*";
    return redisTemplate.keys(redisKey);
  }

  public boolean checkIsEffective(String taskName) {
    String redisKey = SCHEDULE_REDIS_CATALOGUE_PREFIX + ":" + taskName;
    Object getResult = redisUtils.get(redisKey);
    if (getResult == null) {
      return false;
    }
    String redisUUID = (String) getResult;
    String containerUUID = containerInfo.getUuid();
    return containerUUID.equals(redisUUID);
  }


  // TODO DB 開出來要實作
  public void updateLastExecutedDate(
      ScheduleSettingData scheduleSettingData, Date lastExecuteTime) {
    log.warn("ScheduleTool updateLastExecutedDate() func not implements");
  }

  // TODO DB 開出來要實作
  public void createScheduleExecuteRecord(
      Date eventStartTime, ScheduleSettingData scheduleSettingData, EventResult eventResult) {
    log.warn("ScheduleTool createScheduleExecuteRecord() func not implements");
  }

  // TODO 寄信 SendGrid 實作
  public void sendAlarmEmail(ScheduleSettingData scheduleSettingData, EventResult eventResult) {
    log.warn("ScheduleTool sendAlarmEmail() func not implements");
  }
}
