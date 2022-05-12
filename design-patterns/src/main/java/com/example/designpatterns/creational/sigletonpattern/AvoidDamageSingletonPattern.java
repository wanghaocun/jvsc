package com.example.designpatterns.creational.sigletonpattern;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author wanghaocun
 * @since 2022-05-03
 **/
@SuppressWarnings("unused")
public class AvoidDamageSingletonPattern implements Serializable {

    private static AvoidDamageSingletonPattern instance;


    /**
     * 第一种 使用枚举实现单例模式
     *
     * @see EnumerationSingleton
     */
    private enum Singleton {
        // ...
    }

    /**
     * 第二种 在无参构造函数中进行判断 若已有实例 则阻止生成新的实例
     */
    private AvoidDamageSingletonPattern() {
        if (instance == null) {
            throw new RuntimeException("实例已经存在，请通过 getInstance()方法获取");
        }
    }

    /**
     * 第三种
     * 可以不实现序列化接口
     * 如果非得实现序列化接口
     * 可以重写反序列化方法 readResolve()
     * 反序列化时直接返回相关单例对象
     */
    @Serial
    private Object readResolve() throws ObjectStreamException {

        return instance;
    }


}
