package com.wanghaocun.cache;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wanghc
 * @since 2020-11-30
 **/
@SpringBootTest
public class BaseTest {

    @BeforeEach
    public void beforeTest() {
        System.out.println("===>>> unit test start...");
    }

    @AfterEach
    public void afterTest() {
        System.out.println("===>>> unit test done.");
    }

}
