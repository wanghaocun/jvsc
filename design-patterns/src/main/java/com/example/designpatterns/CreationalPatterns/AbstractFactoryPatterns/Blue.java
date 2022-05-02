package com.example.designpatterns.CreationalPatterns.AbstractFactoryPatterns;

/**
 * @author wanghaocun
 * @since 2022-05-02
 **/
public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }

}
