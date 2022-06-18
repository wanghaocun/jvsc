package com.example.javacore;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wanghc
 **/
@Slf4j
public class JavaUtil {

    /**
     * @see System#getProperties()
     */
    static void systemProperties() {
        log.info("java.version: {}", System.getProperty("java.version"));
        log.info("java.vendor: {}", System.getProperty("java.vendor"));
        log.info("java.home: {}", System.getProperty("java.home"));
        log.info("==============================");
        log.info("java.vm.specification.version: {}", System.getProperty("java.vm.specification.version"));
        log.info("java.vm.name: {}", System.getProperty("java.vm.name"));
        log.info("java.vm.vendor: {}", System.getProperty("java.vm.vendor"));
        log.info("==============================");
        log.info("java.class.version: {}", System.getProperty("java.class.version"));
        log.info("java.io.tmpdir: {}", System.getProperty("java.io.tmpdir"));
        log.info("java.compiler: {}", System.getProperty("java.compiler"));
        log.info("==============================");
        log.info("os.name: {}", System.getProperty("os.name"));
        log.info("os.arch: {}", System.getProperty("os.arch"));
        log.info("os.version: {}", System.getProperty("os.version"));
        log.info("native.encoding: {}", System.getProperty("native.encoding"));
        log.info("==============================");
        log.info("System.getenv(): {}", System.getenv());

    }

    /**
     * @see java.util.Arrays
     * @see java.util.Objects
     */
    static void jdkUtil() {
        log.info("==============================");
        log.info("Objects");
        log.info("Arrays");
        log.info("Collections");
        log.info("==============================");
        List<Integer> integers = Arrays.asList(1, 2, 3);
        log.info(integers.toString());
        // immutable
        List<Object> list = Collections.emptyList();
        //list.add(1);
        //list.add(2);
        //list.add(3);
        System.out.println(list);
    }

    /**
     * @see org.springframework.util.StringUtils
     */
    static void springUtil() {
        log.info("==============================");
        log.info("StringUtils");
        log.info("CollectionUtils");
        log.info("FileCopyUtils");
        log.info("==============================");
    }

    /**
     * @see <a href="https://mvnrepository.com/artifact/org.apache.commons/commons-lang3">commons-lang3</a>
     * <dependency>
     * <groupId>org.apache.commons</groupId>
     * <artifactId>commons-lang3</artifactId>
     * <version>3.12.0</version>
     * </dependency>
     */
    static void commonLang3Util() {
        log.info("==============================");
        log.info("StringUtils");
        log.info("CollectionUtils");
        log.info("FileCopyUtils");
        log.info("==============================");
    }

    /**
     * @see <a href="https://mvnrepository.com/artifact/cn.hutool/hutool-all">hutool-all</a>
     * <dependency>
     * <groupId>cn.hutool</groupId>
     * <artifactId>hutool-all</artifactId>
     * <version>5.8.4.M1</version>
     * </dependency>
     */
    static void huToolUtil() {
        log.info("==============================");
        log.info("huToolUtil");
        log.info("==============================");
    }

    public static void main(String[] args) {

        systemProperties();
        jdkUtil();

    }

}
