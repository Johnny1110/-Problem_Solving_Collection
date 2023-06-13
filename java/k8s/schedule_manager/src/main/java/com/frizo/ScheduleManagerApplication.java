package com.frizo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@RestController
public class ScheduleManagerApplication {

  @Autowired private Environment env;

  public static void main(String[] args) {
    SpringApplication.run(ScheduleManagerApplication.class, args);
  }

  public String getEnvProfile() {
    return env.getProperty("profile");
  }

  /**
   * for k8s check health
   *
   * @return
   * @throws Exception
   */
  @GetMapping("/healthcheck")
  String home() throws Exception {
    log.debug("environment profile: {}", getEnvProfile());
    return "Ok!";
  }
}
