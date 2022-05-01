package com.example.redisdistrlockdemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author wanghc
 * @since 2022-01-14
 **/
@Configuration
public class RedisLuaConfig {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public boolean runLuaScript(String lockKey, String value) {
        List<String> keyList = Collections.singletonList(lockKey);

        //执行脚本 删除锁
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/redisLock.lua")));
        redisScript.setResultType(Long.class);
        Long execute = stringRedisTemplate.execute(redisScript, keyList, value);

        assert execute != null;
        return execute==1;
    }

}
