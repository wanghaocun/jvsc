package com.example.designpatterns.StructuralPatterns.BridgePattern;

/**
 * @author wanghaocun
 * @since 2022-05-07
 */
public class RedCircle implements DrawAPI {

    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius + ", x: " + x + ", " + y + "]");
    }

}
