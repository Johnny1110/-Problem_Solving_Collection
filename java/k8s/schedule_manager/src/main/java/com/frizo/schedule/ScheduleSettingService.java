package com.frizo.schedule;

import com.frizo.enums.ScheduleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ScheduleSettingService {

  public List<ScheduleSettingData> findAllScheduleSettingData() {
     //測試使用
                ScheduleSettingData scheduleSettingData = new ScheduleSettingData();
                scheduleSettingData.setScheduleId(1);
                scheduleSettingData.setScheduleSettingId(1);
                scheduleSettingData.setScheduleType(ScheduleType.API);
                scheduleSettingData.setEnable(true);
                scheduleSettingData.setEventMethod("DemoDynmicEvent");
                scheduleSettingData.setTimeoutLimitSec(80);
                scheduleSettingData.setTriggerTime("0/2 * * * * ?");
                scheduleSettingData.setTaskName("demo");
                scheduleSettingData.setScheduleIntervalSec(scheduleSettingData.computeScheduleIntervalSec()); //上次執行距離現在時間
                return List.of(scheduleSettingData);
  }
}
