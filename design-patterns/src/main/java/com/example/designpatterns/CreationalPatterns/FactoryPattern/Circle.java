package com.example.designpatterns.CreationalPatterns.FactoryPattern;

/**
 * @author wanghaocun
 * @since 2022-05-01
 **/
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }

}
