package com.example.streamtest;

import java.util.Optional;

/**
 * @author wanghc
 * @since 2022-04-27
 **/
public class OptionalOperate {

    public static void main(String[] args) {
        String str = null;
        Integer length = Optional.ofNullable(str).map(String::length).orElse(0);
        System.out.println(length);
        // 创建空对象
        Optional<String> empty = Optional.empty();
        // 创建对象 str为null时 将抛出NPE
        Optional<String> str1 = Optional.of("str");
        // 创建对象  str允许为空
        Optional<String> str2 = Optional.ofNullable(str);
        User user = User.builder().age(18).phone(Optional.of(123L)).build();
        System.out.println(Optional.ofNullable(user).map(User::getName).orElse("no name"));
        System.out.println(Optional.ofNullable(user).map(User::getPhone).map(Optional::get).orElse(-1L));
        System.out.println(Optional.ofNullable(user).flatMap(User::getPhone).orElse(-1L));
        Optional.ofNullable(user).filter(user1 -> user1.getAge()>18).ifPresent(user1 -> System.out.println("adult: "+user1));
    }

}
