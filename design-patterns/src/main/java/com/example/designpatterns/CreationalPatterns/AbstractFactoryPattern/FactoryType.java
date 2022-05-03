package com.example.designpatterns.CreationalPatterns.AbstractFactoryPattern;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wanghaocun
 * @since 2022-05-02
 **/
@Getter
@AllArgsConstructor
public enum FactoryType {

    SHAPE,COLOR;

    public static boolean contains(String factory) {
        for (FactoryType value : FactoryType.values()) {
            if (value.toString().equalsIgnoreCase(factory)) {
                return true;
            }
        }
        return false;
    }

}
