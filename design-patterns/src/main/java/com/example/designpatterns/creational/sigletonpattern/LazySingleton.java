package com.example.designpatterns.creational.sigletonpattern;

/**
 * @author wanghaocun
 * @since 2022-05-03
 **/
public class LazySingleton {

    /**
     * 声明 LazySingleton 的一个对象
     */
    private static LazySingleton instance;

    /**
     * 让构造函数为 private，这样该类就不会被实例化
     */
    private LazySingleton() {

    }

    /**
     * 同步获取唯一可用的对象
     *
     * @return LazySingleton
     */
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }

    /**
     * 打印消息
     */
    public void showMessage() {
        System.out.println("hi there, I am lazy pattern.");
    }

}
