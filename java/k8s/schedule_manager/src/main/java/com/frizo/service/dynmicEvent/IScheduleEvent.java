package com.frizo.service.dynmicEvent;

import com.frizo.common.EventMethod;
import com.frizo.schedule.EventResult;
import com.frizo.schedule.ScheduleSettingData;

public interface IScheduleEvent extends EventMethod {

    void init();

    EventResult execute(ScheduleSettingData scheduleSettingData);

}
