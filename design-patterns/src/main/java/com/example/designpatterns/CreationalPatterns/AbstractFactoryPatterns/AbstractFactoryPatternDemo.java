package com.example.designpatterns.CreationalPatterns.AbstractFactoryPatterns;

import com.example.designpatterns.CreationalPatterns.FactoryPatterns.Shape;
import com.example.designpatterns.CreationalPatterns.FactoryPatterns.ShapeType;

import java.util.Objects;

/**
 * @author wanghaocun
 * @since 2022-05-02
 **/
public class AbstractFactoryPatternDemo {

    public static void main(String[] args) {
        // 获取形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory(FactoryType.SHAPE.name());
        Objects.requireNonNull(shapeFactory, "形状工厂不能为空");

        // 获取形状为 Circle 的对象
        Shape shape1 = shapeFactory.getShape(ShapeType.CIRCLE.getType());
        // 调用 Circle 的 draw 方法
        shape1.draw();

        // 获取形状为 Rectangle 的对象
        Shape shape2 = shapeFactory.getShape(ShapeType.RECTANGLE.getType());
        // 调用 Rectangle 的 draw 方法
        shape2.draw();

        // 获取形状为 Square 的对象
        Shape shape3 = shapeFactory.getShape(ShapeType.SQUARE.getType());
        // 调用 Square 的 draw 方法
        shape3.draw();


        // 获取颜色工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory(FactoryType.COLOR.name());
        Objects.requireNonNull(colorFactory, "颜色工厂不能为空");

        // 获取颜色为 Red 的对象
        Color color1 = colorFactory.getColor(ColorType.RED.name());
        // 调用 Red 的 fill 方法
        color1.fill();

        // 获取颜色为 Green  的对象
        Color color2 = colorFactory.getColor(ColorType.GREEN.name());
        // 调用 Green  的 fill 方法
        color2.fill();

        // 获取颜色为 Blue  的对象
        Color color3 = colorFactory.getColor(ColorType.BLUE.name());
        // 调用 Blue  的 fill 方法
        color3.fill();


        // 获取未定义工厂
        AbstractFactory undefinedFactory = FactoryProducer.getFactory("undefined");
        Objects.requireNonNull(undefinedFactory, "未定义工厂不能为空");

        // 获取颜色为 Red 的对象
        Color color = undefinedFactory.getColor(ColorType.RED.name());
        // 调用 Red 的 fill 方法
        color.fill();

        // 获取形状为 Circle 的对象
        Shape shape = undefinedFactory.getShape(ShapeType.CIRCLE.getType());
        // 调用 Circle 的 draw 方法
        shape.draw();

    }

}
