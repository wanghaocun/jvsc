package com.example.designpatterns.CreationalPatterns.FactoryPattern;

import com.example.designpatterns.CreationalPatterns.AbstractFactoryPattern.AbstractFactory;
import com.example.designpatterns.CreationalPatterns.AbstractFactoryPattern.Color;

import java.util.Objects;

/**
 * @author wanghaocun
 * @since 2022-05-01
 **/
public class ShapeFactory extends AbstractFactory {

    /**
     * 使用 getShape 方法获取形状的对象
     *
     * @param shapeType String
     * @return Shape
     */
    @Override
    public Shape getShape(String shapeType) {
        shapeType = Objects.requireNonNullElse(shapeType, "null");
        //Assert.isTrue(ShapeType.contains(shapeType.toUpperCase()), ()->String.format("shape [%s] invalid",shapeType));

        if (ShapeType.contains(shapeType.toUpperCase())) {
            ShapeType shapeTypeEnum = ShapeType.valueOf(shapeType.toUpperCase());

            return switch (shapeTypeEnum) {
                case CIRCLE -> new Circle();
                case SQUARE -> new Square();
                case RECTANGLE -> new Rectangle();
            };
        } else {
            System.out.printf("shape [%s] invalid%n", shapeType);
        }

        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }

}
