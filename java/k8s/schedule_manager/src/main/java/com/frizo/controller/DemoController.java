package com.frizo.controller;

import com.frizo.component.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
public class DemoController {

  @Autowired private Environment env;

  @Autowired private RedisUtils redisUtils;

  @GetMapping("/public/v1/env")
  public Object hello() {
    return env.getProperty("profile");
  }

  @GetMapping("public/v1/test/redis")
  public Object redis(String key, String value) {
    return redisUtils.set(key, value);
  }
}
