package com.example.designpatterns.creational.factorypattern;

import java.util.Objects;

/**
 * @author wanghaocun
 * @since 2022-05-02
 **/
public class FactoryPatternsDemo {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        for (ShapeType shapeType : ShapeType.values()) {
            Shape shape = shapeFactory.getShape(shapeType.getType());
            shape.draw();
        }

        Shape circle = shapeFactory.getShape("shapeType");
        if (Objects.nonNull(circle)) {
            circle.draw();
        }

    }

}
