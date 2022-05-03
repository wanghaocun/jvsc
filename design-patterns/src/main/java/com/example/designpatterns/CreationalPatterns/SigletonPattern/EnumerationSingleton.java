package com.example.designpatterns.CreationalPatterns.SigletonPattern;

/**
 * 枚举类实现单例模式是 《effective java》作者极力推荐的单例实现模式，
 * 因为枚举类型是线程安全的，并且只会装载一次，
 * 设计者充分的利用了枚举的这个特性来实现单例模式，
 * 枚举的写法非常简单，
 * 而且枚举类型是所用单例实现中唯一一种不会被破坏的单例实现模式。
 * <p>
 * 在实现过程中，**Java虚拟机会保证枚举类型不能被反射并且构造函数只被执行一次**。
 *
 * @author wanghaocun
 * @since 2022-05-03
 */
public class EnumerationSingleton {

    /**
     * 让构造函数为 private，这样该类就不会被实例化
     */
    private EnumerationSingleton() {

    }

    /**
     * 获取枚举中唯一可用的当前类对象
     *
     * @return EnumerationSingleton
     */
    public static EnumerationSingleton getInstance() {

        return SingletonEnum.INSTANCE.getInstance();
    }

    /**
     * 打印消息
     */
    public void showMessage() {
        System.out.println("hi there, I am enumeration pattern.");
    }

    /**
     * 内部枚举类
     */
    private enum SingletonEnum {

        /**
         * 枚举实例
         */
        INSTANCE;

        /**
         * 内部变量
         */
        private final EnumerationSingleton instance;

        /**
         * 利用枚举的构造函数实例化当前类赋值给内部变量
         */
        SingletonEnum() {
            instance = new EnumerationSingleton();
        }

        /**
         * 获取枚举内实例化的当前类对象
         *
         * @return EnumerationSingleton
         */
        private EnumerationSingleton getInstance() {

            return instance;
        }

    }

}
