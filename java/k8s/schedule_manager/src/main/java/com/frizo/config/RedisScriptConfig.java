package com.frizo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.script.RedisScript;

@Configuration
public class RedisScriptConfig {
    @Bean(name = "scheduleRegistScript")
    public RedisScript<Boolean> scheduleRegistScript() {
        Resource scriptSource = new ClassPathResource("scripts/scheduleRegistScript.lua");
        return RedisScript.of(scriptSource, Boolean.class);
    }

}
