package com.example.designpatterns.creational.sigletonpattern;

/**
 * @author wanghaocun
 * @since 2022-05-03
 */
public class SingletonPatternDemo {

    public static void main(String[] args) {

        // 懒汉式
        LazySingleton.getInstance().showMessage();
        // 饿汉式
        EagerSingleton.getInstance().showMessage();
        // DCL式
        DclSingleton.getInstance().showMessage();
        // 静态内部类式
        StaticInnerClassSingleton.getInstance().showMessage();
        // 枚举式
        EnumerationSingleton.getInstance().showMessage();

    }

}
