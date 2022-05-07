package com.example.designpatterns.StructuralPatterns.BridgePattern;

/**
 * @author wanghaocun
 * @since 2022-05-07
 */
public class BridgePatternDemo {

    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }

}
