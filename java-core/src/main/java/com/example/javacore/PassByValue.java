package com.example.javacore;

import lombok.Data;

/**
 * @author wanghc
 * @link <a href="https://www.digitalocean.com/blog/journaldev-joins-digitalocean">JournalDev joins DigitalOcean</a>
 * @see <a href="https://www.journaldev.com/3884/java-is-pass-by-value-and-not-pass-by-reference">ref</a>
 * @see <a href="https://segmentfault.com/a/1190000016773324">ref</a>
 **/
public class PassByValue {

    @Data
    static class Person {
        private String name;
        private int age;
    }

    @SuppressWarnings("unused")
    public static void PersonCrossTest1(Person person) {
        System.out.println("传入的person的name：" + person.getName());
        person.setName("我是张小龙");
        System.out.println("方法内重新赋值后的name：" + person.getName());
    }

    @SuppressWarnings("unused")
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
