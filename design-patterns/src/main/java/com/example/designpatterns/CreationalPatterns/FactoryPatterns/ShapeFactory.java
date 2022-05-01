package com.example.designpatterns.CreationalPatterns.FactoryPatterns;

import org.springframework.lang.NonNull;

import java.util.Objects;

/**
 * @author wanghaocun
 * @since 2022-05-01
 **/
public class ShapeFactory {

    // 使用 getShape 方法获取形状的对象
    public Shape getShape(@NonNull String shapeType) {
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

}
