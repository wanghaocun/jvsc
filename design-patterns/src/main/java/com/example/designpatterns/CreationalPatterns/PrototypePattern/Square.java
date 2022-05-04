package com.example.designpatterns.CreationalPatterns.PrototypePattern;

/**
 * @author wanghaocun
 * @since 2022-05-04
 */
public class Square extends Shape {

    public Square() {
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }

}
