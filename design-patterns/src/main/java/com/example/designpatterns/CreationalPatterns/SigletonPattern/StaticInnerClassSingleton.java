package com.example.designpatterns.CreationalPatterns.SigletonPattern;

/**
 * @author wanghaocun
 * @since 2022-05-03
 **/
public class StaticInnerClassSingleton {

    /**
     * 静态内部类实例化此类
     */
    private static class SingletonHolder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    /**
     * 让构造函数为 private，这样该类就不会被实例化
     */
    private StaticInnerClassSingleton() {

    }

    /**
     * 获取内部类初始化后唯一可用的对象
     *
     * @return StaticInnerClassSingleton
     */
    public static StaticInnerClassSingleton getInstance() {

        return SingletonHolder.INSTANCE;
    }

    /**
     * 打印消息
     */
    public void showMessage() {
        System.out.println("hi there, I am static inner class pattern.");
    }

}
