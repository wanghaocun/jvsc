package com.example.designpatterns.creational.abstractfactorypattern;

import com.example.designpatterns.creational.factorypattern.ShapeFactory;

import java.util.Objects;

/**
 * @author wanghaocun
 * @since 2022-05-02
 **/
public class FactoryProducer {

    public static AbstractFactory getFactory(String choice) {
        choice = Objects.requireNonNullElse(choice, "null");

        if (FactoryType.contains(choice)) {
            FactoryType factoryType = FactoryType.valueOf(choice.toUpperCase());

           return switch (factoryType) {
                case SHAPE -> new ShapeFactory();
                case COLOR -> new ColorFactory();
            };
        } else {
            System.out.printf("factory [%s] invalid%n", choice);
        }

        return null;
    }

}
