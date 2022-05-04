package com.example.designpatterns.CreationalPatterns.PrototypePattern;

/**
 * @author wanghaocun
 * @since 2022-05-04
 */
public class Rectangle extends Shape {

    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }

}
