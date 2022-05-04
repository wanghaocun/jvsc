package com.example.designpatterns.CreationalPatterns.PrototypePattern;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wanghaocun
 * @since 2022-05-04
 */
public abstract class Shape implements Cloneable {

    @Getter
    @Setter
    private String id;

    @Getter
    protected String type;

    abstract void draw();

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

}
