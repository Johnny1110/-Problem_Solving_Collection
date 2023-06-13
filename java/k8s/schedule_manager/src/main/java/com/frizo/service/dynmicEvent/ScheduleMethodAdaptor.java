package com.frizo.service.dynmicEvent;

import com.frizo.common.AbstractEventMethodAdaptor;
import com.frizo.common.EventMethodRegister;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Set;

@Slf4j
public class ScheduleMethodAdaptor extends AbstractEventMethodAdaptor<IScheduleEvent> {

  public ScheduleMethodAdaptor(EventMethodRegister<IScheduleEvent> eventMethodRegister) {
    super(eventMethodRegister);
  }

  public Optional<IScheduleEvent> getScheduleEventMethod(String methodName) {
    IScheduleEvent event = getEventMethod(methodName);
    if (event == null) {
      throw new RuntimeException(
          String.format(
              "ScheduleMethodAdaptor.getScheduleEventMethod() failed, because input methodName:%s not match any IScheduleEvent.",
              methodName));
    }
    return Optional.of(event);
  }

  public Set<String> getAllEventMethods() {
    return getAllEventMethodName();
  }
}
