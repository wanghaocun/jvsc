package com.example.designpatterns.StructuralPatterns.BridgePattern;

/**
 * @author wanghaocun
 * @since 2022-05-07
 */
public class Circle extends Shape {

    private final int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }

}
