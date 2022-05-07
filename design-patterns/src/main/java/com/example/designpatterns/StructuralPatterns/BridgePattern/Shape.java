package com.example.designpatterns.StructuralPatterns.BridgePattern;

/**
 * @author wanghaocun
 * @since 2022-05-07
 */
public abstract class Shape {

    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();

}
