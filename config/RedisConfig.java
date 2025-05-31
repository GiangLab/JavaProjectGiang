package com.example.GiangFroject.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RedisConfig {
    // Spring Boot auto-config sẽ xử lý connectionFactory, redisTemplate,...
}

