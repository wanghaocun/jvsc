package com.example.redisdistrlockdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author wanghc
 * @since 2022-01-14
 **/
@RestController
public class SimulateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulateController.class);

    @Resource
    private RedisLockServer redisLockServer;

    @PostMapping("/lock")
    public String reduceSku() throws InterruptedException {
        final String key = "distributed";
        String value = UUID.randomUUID().toString().replace("-", "");
        boolean lock = redisLockServer.setLock(key, value, 10, TimeUnit.SECONDS);
        if (lock) {
            LOGGER.info("{} ===>>> 加锁成功", key);

            TimeUnit.SECONDS.sleep(8);

            if (redisLockServer.deleteLock(key, value)) {
                LOGGER.info("{} ===>>> 删除锁成功", key);
            }
        } else {
            LOGGER.info("{} ===>>> 加锁失败，等待重试...", key);
        }

        return "%b ===>> 操作结束".formatted(lock);
    }

}
