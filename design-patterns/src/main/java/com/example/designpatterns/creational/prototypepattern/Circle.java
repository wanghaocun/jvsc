package com.example.designpatterns.creational.prototypepattern;

/**
 * @author wanghaocun
 * @since 2022-05-04
 */
public class Circle extends Shape {

    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }

}
