package com.frizo.common;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EventMethodRegister<T extends EventMethod> {

  private final Map<String, T> eventMethodMap;

  public EventMethodRegister() {
    this.eventMethodMap = new HashMap<>();
  }

  public void add(T eventMethod) {
    if (StringUtils.isEmpty(eventMethod.getName())) {
      String className = eventMethod.getClass().getSimpleName();
      eventMethodMap.put(className, eventMethod);
    } else {
      eventMethodMap.put(eventMethod.getName(), eventMethod);
    }
  }

  public void add(String eventMethodName, T eventMethod) {
    if (StringUtils.isEmpty(eventMethodName)) {
      add(eventMethod);
    } else {
      eventMethodMap.put(eventMethodName, eventMethod);
    }
  }

  public void remove(String eventMethodName) {
    eventMethodMap.remove(eventMethodName);
  }

  public T get(String eventMethodName) {
    T t = eventMethodMap.get(eventMethodName);
    if (t == null) {
      throw new RuntimeException(
          String.format(
              "failed to get EventMethod from EventMethodRegister, eventMethodName:%s not exist.",
              eventMethodName));
    } else {
      return t;
    }
  }

  public Set<String> getAllEventMethodName(){
    return eventMethodMap.keySet();
  }
}
