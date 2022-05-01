package com.example.streamtest;

/**
 * 苹果过滤接口
 *
 * @author zhenchao.wang 2016-09-17 14:21
 * @version 1.0.0
 */
@FunctionalInterface
public interface AppleFilter {
 
    /**
     * 筛选条件抽象
     *
     * @param apple
     * @return
     */
    boolean accept(Apple apple);
 
}
 
