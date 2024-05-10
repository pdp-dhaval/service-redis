package com.opl.serviceredis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisCacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisCacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Boolean keyExists(String key) {
        return redisTemplate.hasKey(key);
    }

    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(o.toString(), entityClass);
        } catch (Exception e) {
            log.error("Exception While setting key in Redis ", e);
            return null;
        }
    }

    public void set(String key, Object object, Long ttl) {
        try {
            redisTemplate.opsForValue().set(key, new ObjectMapper().writeValueAsString(object), ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception While setting key in Redis ", e);
        }
    }


    public void setList(String key, List<?> list, Long ttl) {
        try {
            redisTemplate.opsForValue().set(key, new ObjectMapper().writeValueAsString(list));
        } catch (JsonProcessingException e) {
            log.error("Exception While setting key in Redis ", e);
        }
        redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
    }

    public <T> List<T> getList(String key, Class<T> elementType) {
        String value = (String) redisTemplate.opsForValue().get(key);
        if (value != null) {
            try {
                return new ObjectMapper().readValue(value, new TypeReference<List<T>>() {
                });
            } catch (JsonProcessingException e) {
                log.error("Exception While setting key in Redis ", e);
            }
        }
        return null;
    }
}
