package com.frizo.schedule;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class MyApplicationReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final ApplicationScheduleCore applicationScheduleCore;

    public MyApplicationReadyEvent(ApplicationScheduleCore applicationScheduleCore) {
        this.applicationScheduleCore = applicationScheduleCore;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.debug("[MyApplicationListener] onApplicationEvent() 註冊 QuartzTaskManager 開始。");
        applicationScheduleCore.startup();
        log.debug("[MyApplicationListener] onApplicationEvent() 註冊 QuartzTaskManager 完成。");
    }

    @PreDestroy
    public void onShutdown() {
        log.debug("[MyApplicationListener] onShutdown() 關閉 QuartzTaskManager 開始。");
        applicationScheduleCore.shutdown();
        log.debug("[MyApplicationListener] onShutdown() 關閉 QuartzTaskManager 完成。");
    }

}
