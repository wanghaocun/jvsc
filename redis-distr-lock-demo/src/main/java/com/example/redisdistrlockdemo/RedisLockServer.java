package com.example.redisdistrlockdemo;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author wanghc
 * @since 2022-01-14
 **/
@Component
public class RedisLockServer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisLuaConfig redisLuaConfig;

    public boolean setLock(String lockKey, String value, long time, TimeUnit timeUnit) {
        return Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(lockKey, value, time, timeUnit));
    }

    public boolean deleteLock(String lockKey, String value) {
        return redisLuaConfig.runLuaScript(lockKey, value);
    }

}
