package com.example.designpatterns.creational.sigletonpattern;

/**
 * 双检锁/双重校验锁
 * Double-Checked Locking
 *
 * @author wanghaocun
 * @since 2022-05-03
 **/
public class DclSingleton {

    /**
     * 声明 DclSingleton 的一个对象
     */
    private volatile static DclSingleton instance;

    /**
     * 让构造函数为 private，这样该类就不会被实例化
     */
    private DclSingleton() {

    }

    /**
     * 同步获取唯一可用的对象
     *
     * @return DclSingleton
     */
    public static synchronized DclSingleton getInstance() {
        if (instance == null) {
            synchronized (DclSingleton.class) {
                if (instance == null) {
                    instance = new DclSingleton();
                }
            }
        }

        return instance;
    }

    /**
     * 打印消息
     */
    public void showMessage() {
        System.out.println("hi there, I am DCL pattern.");
    }

}
