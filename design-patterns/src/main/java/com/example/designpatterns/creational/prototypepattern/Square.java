package com.example.designpatterns.creational.prototypepattern;

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
