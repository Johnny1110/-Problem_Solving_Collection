package com.frizo.schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Configuration
@EnableScheduling
public class QuartzScheduleConfig {

  /** 初始化時 對 container 的配置
   * @param env
   * @return
   */
  @Bean("containerInfo")
  public ContainerInfo containerInfo(Environment env) {
    Integer containerQty =
        Integer.valueOf(Objects.requireNonNull(env.getProperty("schedule_manager.container_qty")));
    UUID uuid = UUID.randomUUID();
    return new ContainerInfo(new Date(), uuid.toString(), containerQty, new AtomicBoolean(true));
  }
}
