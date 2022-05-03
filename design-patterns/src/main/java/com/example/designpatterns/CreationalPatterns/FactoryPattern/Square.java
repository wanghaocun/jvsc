package com.example.designpatterns.CreationalPatterns.FactoryPattern;

/**
 * @author wanghaocun
 * @since 2022-05-01
 **/
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }

}
