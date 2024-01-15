package com.frizo.enums;

public enum ScheduleType {

  API(1, "API 抓資料"),
  CACULATE(2, "統計");

  private final Integer code;

  private final String desc;

  ScheduleType(Integer code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  private Integer code() {
    return this.code;
  }

  private String desc() {
    return this.desc;
  }

  public static ScheduleType getByCode(Integer code) {
    for (ScheduleType value : values()) {
      if (value.code.equals(code)) {
        return value;
      }
    }
    return null;
  }
}
