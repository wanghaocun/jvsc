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
     * optional是final类 没有任何继承和实现 所以如果序列化类就会出现问题
     * optional在设计的时候就没有考虑将它作为类的字段使用 可以采用替换策略
     *
     *     private long phone;
     *     public Optional<Long> getPhone() {
     *         return Optional.ofNullable(this.phone);
     *     }
     */
    private Optional<Long> phone;

    private Optional<String> email;


}
