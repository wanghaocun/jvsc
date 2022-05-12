package com.example.designpatterns.creational.abstractfactorypattern;

import com.example.designpatterns.creational.factorypattern.Shape;

/**
 * @author wanghaocun
 * @since 2022-05-02
 **/
public abstract class AbstractFactory {

    public abstract Shape getShape(String shapeType);

    public abstract Color getColor(String colorType);

}
