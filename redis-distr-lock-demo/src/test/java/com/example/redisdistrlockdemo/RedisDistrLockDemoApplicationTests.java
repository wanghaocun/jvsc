package com.example.redisdistrlockdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Random;

@SpringBootTest
class RedisDistrLockDemoApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Test
    void contextLoads() {

        Random random = new Random();
        for (int i = 0; i < 363; i++) {

            int a = random.nextInt(30, 100);
            stringRedisTemplate.opsForList().leftPush("3327dc10c2704579:AWARD_POOL_REDPACKET_LIST_1265_2022-05-11",
                    String.valueOf(a));
            System.out.print(a+", ");

        }

    }

}
