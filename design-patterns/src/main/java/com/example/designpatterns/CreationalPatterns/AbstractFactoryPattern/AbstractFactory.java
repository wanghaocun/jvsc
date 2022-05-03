package com.example.designpatterns.CreationalPatterns.AbstractFactoryPattern;

import com.example.designpatterns.CreationalPatterns.FactoryPattern.Shape;

/**
 * @author wanghaocun
 * @since 2022-05-02
 **/
public abstract class AbstractFactory {

    public abstract Shape getShape(String shapeType);

    public abstract Color getColor(String colorType);

}
