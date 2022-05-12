package com.example.javacore;

import lombok.Data;

/**
 * @author wanghc
 * @date 2022-05-12
 * {@link} <a href="https://segmentfault.com/a/1190000016773324">ref</a>
 **/
public class PassByValue {

    @Data
    static class Person {
        private String name;
        private int age;
    }

    public static void PersonCrossTest1(Person person) {
        System.out.println("传入的person的name：" + person.getName());
        person.setName("我是张小龙");
        System.out.println("方法内重新赋值后的name：" + person.getName());
    }

    static void PersonCrossTest2(Person person) {
        System.out.println("传入的person的name：" + person.getName());
        // 加多此行代码
        person = new Person();
        person.setName("我是张小龙");
        System.out.println("方法内重新赋值后的name：" + person.getName());
    }

    static void main(String[] args) {
        Person p = new Person();
        p.setName("我是马化腾");
        p.setAge(45);
        //PersonCrossTest1(p);
        PersonCrossTest2(p);
        System.out.println("方法执行后的name：" + p.getName());
    }

}
