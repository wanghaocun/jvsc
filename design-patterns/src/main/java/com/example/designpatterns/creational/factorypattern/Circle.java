package com.example.designpatterns.creational.factorypattern;

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
