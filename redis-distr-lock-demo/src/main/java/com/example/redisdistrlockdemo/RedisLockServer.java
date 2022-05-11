package com.example.redisdistrlockdemo;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;
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


    //public static void main(String[] args) {
    //
    //
    //    Random random = new Random();
    //    for (int i = 0; i < 369; i++) {
    //
    //        int a = random.nextInt(30, 100);
    //        stringRedisTemplate.opsForList().leftPush("3327dc10c2704579:AWARD_POOL_REDPACKET_LIST_1265_2022-05-11",
    //                String.valueOf(a));
    //        System.out.print(a+", ");
    //
    //    }
    //}
}
