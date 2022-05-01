package com.example.streamtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Objects;

@SpringBootTest
class StreamTestApplicationTests {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void zsetExistValue() {
         redisTemplate.opsForZSet().add("plan:detection:task-queue", "234",123456);
         redisTemplate.opsForZSet().add("plan:detection:task-queue", "2345",1234567);
        Double score = redisTemplate.opsForZSet().score("plan:detection:task-queue", "2345");
        Long rank = redisTemplate.opsForZSet().rank("plan:detection:task-queue", "2345");
        System.out.println(Objects.isNull(score));
        System.out.println(Objects.isNull(rank));
    }


}
