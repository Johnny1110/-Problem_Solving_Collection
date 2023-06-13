package com.frizo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * 這個RedisConfiguration類擴展了CachingConfigurerSupport並實現了CachingConfigurer介面。在該類中，我們配置了RedisTemplate和CacheManager，並且還提供了自定義的CacheErrorHandler和KeyGenerator方法。
 *
 * <p>在RedisTemplate的配置中，我們使用了StringRedisSerializer作為鍵和值的序列化器，但您可以根據需要使用其他序列化器。
 *
 * <p>CacheManager的配置使用了RedisCacheConfiguration，並將鍵和值的序列化器設置為StringRedisSerializer。
 *
 * <p>請確保在您的Spring Boot應用程序中，將RedisConfiguration類所在的包包含在組件掃描的範圍內，以便Spring能夠正確地識別並應用Redis相關的配置。
 *
 * <p>注意，您需要確保已經將相關的Redis相依項目添加到您的專案的依賴中。您可以根據需要調整和擴展這個配置類，以滿足您的具體需求。
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

  @Bean
  public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(connectionFactory);
    // Use Jackson2JsonRedisSerializer to replace the default JdkSerializationRedisSerializer to
    // serialize and deserialize the Redis value.
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer<>(Object.class);
    ObjectMapper mapper = new ObjectMapper();
    mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    mapper.activateDefaultTyping(
        LaissezFaireSubTypeValidator.instance,
        ObjectMapper.DefaultTyping.NON_FINAL,
        JsonTypeInfo.As.PROPERTY);
    jackson2JsonRedisSerializer.setObjectMapper(mapper);
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    // String serialization of keys
    redisTemplate.setKeySerializer(stringRedisSerializer);
    // String serialization of hash keys
    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    // Jackson's serialization of values
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    // Jackson's serialization of hash values
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(LettuceConnectionFactory connectionFactory) {
    RedisCacheConfiguration cacheConfiguration =
        RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofHours(1L))
            .disableCachingNullValues()
            .computePrefixWith(cacheName -> "frizo".concat(":").concat(cacheName).concat(":"))
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    new StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    new GenericJackson2JsonRedisSerializer()));

    return RedisCacheManager.builder(connectionFactory).cacheDefaults(cacheConfiguration).build();
  }

  @Override
  public CacheErrorHandler errorHandler() {
    // Custom error handler if needed
    return super.errorHandler();
  }

  /**
   * 自動建立redis key值
   *
   * @return
   */
  @Override
  public KeyGenerator keyGenerator() {
    return (target, method, params) -> {
      StringBuilder sb = new StringBuilder();
      sb.append(target.getClass().getName());
      sb.append(method.getName());
      for (Object obj : params) {
        sb.append(obj.toString());
      }
      return sb.toString();
    };
  }
}
