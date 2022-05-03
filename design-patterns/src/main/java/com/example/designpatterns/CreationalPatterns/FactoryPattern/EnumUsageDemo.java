package com.example.designpatterns.CreationalPatterns.FactoryPattern;

import java.util.Arrays;
import java.util.List;

/**
 * 如果某个实现了常量接口的类被修改不再需要常量了，
 * 也会因为序列化兼容原因不得不保持该实现，
 * 而且非final类实现常量接口会导致所有子类被污染
 * --《Effective Java》
 * <p>
 * 接口中的常量, 不应该用于全局. 而应当用在与之相关的类中。
 * 这样能保持很好的层级关系和隔离性，方便分包和扩展，具体请参考spring中对常量维护的原则
 * <p>
 * 大力推荐枚举类型的使用，让他无处不在的去管理你的代码，可以让代码结构更加清晰易懂可扩展。
 * 而常量类可以收集管理一些比较杂的一些常量。
 * 而接口中的常量，在遵循开闭原则的基础上，向上抽象管理自己的内聚的常量
 *
 * @author wanghaocun
 * @since 2022-05-02
 */
public class EnumUsageDemo {

    public static void main(String[] args) {
        // 注意summer是小写
        List<String> params = Arrays.asList("Spring", "summer");
        for (String name : params) {
            // 查找表面值与name相同的枚举项
            if (Season.contains(name)) {
                Season s = Season.valueOf(name);
                System.out.println(s);
            } else {
                // 没有该枚举项时的逻辑处理
                System.out.println("无相关枚举项");
            }
        }

        System.out.printf("最舒服的季节：%s", Season.getComfortableSeason());

    }

    /**
     * 季节枚举
     */
    enum Season {

        Spring, Summer, Autumn, Winter;

        /**
         * 使用枚举前应先判断下 避免异常导致程序中断
         * IllegalArgumentException: No enum constant...
         *
         * @param _name String
         * @return boolean
         */
        public static boolean contains(String _name) {
            Season[] season = values();
            for (Season s : season) {
                if (s.name().equals(_name)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 最舒服的季节
         */
        public static Season getComfortableSeason() {
            return Spring;
        }

    }

}


