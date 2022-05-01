package com.example.streamtest;

import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author wanghc
 * @since 2022-04-26
 **/
public class RedisOperate {

    @Resource
    private StringRedisTemplate redisTemplate;

    /**
     * zset是否存在key
     * @param key String
     * @param value String
     * @return boolean
     */
    private boolean zrank(String key, String value) {
        return Objects.isNull(redisTemplate.opsForZSet().rank(key, value));
    }

    /**
     * zset是否存在key
     * @param key String
     * @param value String
     * @return boolean
     */
    private boolean zscore(String key, String value) {
        return Objects.isNull(redisTemplate.opsForZSet().score(key, value));
    }

}
