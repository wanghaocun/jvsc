package com.example.streamtest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

/**
 * @author wanghc
 *   @since 2022-04-27
 */
@Data
@Builder
@AllArgsConstructor
public class User {

    private long id;

    private String name;

    private int age;

    /**
     * optional是final类 没有任何继承和实现 设计时就不打算作为类字段使用
     * 所以如果序列化类就会出现问题  可以采用替换策略
     *
     *     private long phone;
     *     public Optional<Long> getPhone() {
     *         return Optional.ofNullable(this.phone);
     *     }
     */
    private Optional<Long> phone;

    private Optional<String> email;


}
