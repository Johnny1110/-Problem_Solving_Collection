package com.frizo.schedule;

import com.frizo.enums.ScheduleType;
import lombok.Data;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

@Data
public class ScheduleSettingData {

  private Integer scheduleId;

  private Integer scheduleSettingId;

  private String taskName;

  private ScheduleType scheduleType;

  private String description;

  private String triggerTime;

  private Boolean enable;

  private Date lastExecuteTime;

  private Date lastUpdateTime;

  private String sourceCode;

  private String currencyCode;

  private String exchangeCurrencyCode;

  private String paramJson;

  private String eventMethod;

  private Integer timeoutLimitSec;

  /** 每一次執行的識別碼*/
  private String scheduleInstanceId;

  private Long scheduleIntervalSec;

  /** 排程啟動時間（以系統時間登記）*/
  private Date eventStartTime;

  public long computeScheduleIntervalSec() {
    CronExpression cron = null;
    try {
      cron = new CronExpression(this.triggerTime);
      Date currentDate = new Date();
      Date nextExecutionDate = cron.getNextValidTimeAfter(currentDate);
      Date nextNextExecutionDate = cron.getNextValidTimeAfter(nextExecutionDate);
      long intervalInMillis = nextNextExecutionDate.getTime() - nextExecutionDate.getTime();
      return intervalInMillis / 1000;
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

}
