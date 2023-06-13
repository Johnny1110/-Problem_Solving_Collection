package com.frizo.service.dynmicEvent;

import com.frizo.common.EventMethodRegister;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Map;

@Configuration
public class ScheduleMethodConfig {

    private final ApplicationContext applicationContext;

    public ScheduleMethodConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // 凡是實現了 IScheduleEvent 的 Bean 都會被註冊起來，允許 ScheduleMethodAdaptor 做適配。
    @Bean
    @Order
    public ScheduleMethodAdaptor scheduleMethodAdaptor() {
        EventMethodRegister<IScheduleEvent> eventMethodRegister = new EventMethodRegister<>();
        Map<String, IScheduleEvent> eventMap = applicationContext.getBeansOfType(IScheduleEvent.class);
        eventMap.forEach(
                (key, event) -> {
                    System.out.println("key:" + key + " event:" + event.getName());
                    eventMethodRegister.add(event);
                });
        return new ScheduleMethodAdaptor(eventMethodRegister);
    }

}
