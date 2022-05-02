package com.example.designpatterns.CreationalPatterns.AbstractFactoryPatterns;

import com.example.designpatterns.CreationalPatterns.FactoryPatterns.Shape;

/**
 * @author wanghaocun
 * @since 2022-05-02
 **/
public abstract class AbstractFactory {

    public abstract Shape getShape(String shapeType);

    public abstract Color getColor(String colorType);

}
