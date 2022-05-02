package com.example.designpatterns.CreationalPatterns.AbstractFactoryPatterns;

/**
 * @author wanghaocun
 * @since 2022-05-02
 **/
public enum ColorType {

    RED,GREEN,BLUE;

    public static boolean contains(String color) {
        for (ColorType value : ColorType.values()) {
            if (value.toString().equalsIgnoreCase(color)) {
                return true;
            }
        }
        return false;
    }

}
