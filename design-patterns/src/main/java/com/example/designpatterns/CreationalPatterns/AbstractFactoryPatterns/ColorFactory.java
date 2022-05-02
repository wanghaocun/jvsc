package com.example.designpatterns.CreationalPatterns.AbstractFactoryPatterns;

import com.example.designpatterns.CreationalPatterns.FactoryPatterns.Shape;

import java.util.Objects;

/**
 * @author wanghaocun
 * @since 2022-05-02
 **/
public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shape) {
        return null;
    }

    @Override
    public Color getColor(String colorType) {
        colorType = Objects.requireNonNullElse(colorType, "null");

        if (ColorType.contains(colorType)) {
            ColorType color = ColorType.valueOf(colorType.toUpperCase());

            return switch (color) {
                case RED -> new Red();
                case GREEN -> new Green();
                case BLUE -> new Blue();
            };
        } else {
            System.out.printf("color [%s] invalid%n", colorType);
        }

        return null;
    }

}
