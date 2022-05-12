package com.example.designpatterns.creational.sigletonpattern;

/**
 * @author wanghaocun
 * @since 2022-05-03
 **/
public class EagerSingleton {

    /**
     * 声明 EagerSingleton 的一个对象
     */
    private static final EagerSingleton instance = new EagerSingleton();

    /**
     * 让构造函数为 private，这样该类就不会被实例化
     */
    private EagerSingleton() {

    }

    /**
     * 同步获取唯一可用的对象
     *
     * @return EagerSingleton
     */
    public static synchronized EagerSingleton getInstance() {

        return instance;
    }

    /**
     * 打印消息
     */
    public void showMessage() {
        System.out.println("hi there, I am eager pattern.");
    }

}
