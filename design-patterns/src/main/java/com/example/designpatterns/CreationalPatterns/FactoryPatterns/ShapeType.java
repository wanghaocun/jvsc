package com.example.designpatterns.CreationalPatterns.FactoryPatterns;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wanghaocun
 * @since 2022-05-01
 **/
@Getter
@AllArgsConstructor
public enum ShapeType {

    CIRCLE("circle"),
    SQUARE("square"),
    RECTANGLE("rectangle");

    private final String type;

    /**
     * 使用枚举前 先判断下
     * 避免 IllegalArgumentException: No enum constant...
     *
     * @param name String
     * @return boolean
     */
    public static boolean contains(String name) {
        for (ShapeType value : ShapeType.values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}
