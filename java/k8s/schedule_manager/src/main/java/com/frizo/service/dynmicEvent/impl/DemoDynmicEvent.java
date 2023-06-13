package com.frizo.service.dynmicEvent.impl;

import com.frizo.schedule.ContainerInfo;
import com.frizo.schedule.EventResult;
import com.frizo.schedule.ScheduleSettingData;
import com.frizo.service.dynmicEvent.InterruptableScheduleEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DemoDynmicEvent extends InterruptableScheduleEvent {

  public DemoDynmicEvent(ContainerInfo containerInfo) {
    super(containerInfo);
  }

  @Override
  public void init() {}

  @Override
  public EventResult run(ScheduleSettingData scheduleSettingData) {
    EventResult result = new EventResult();
    log.debug("[DemoSchedule] run...");
    System.out.println("[DemoSchedule] run...");
    result.setResult(true);
    return result;
  }

  @Override
  public String getName() {
    return "DemoDynmicEvent";
  }
}
