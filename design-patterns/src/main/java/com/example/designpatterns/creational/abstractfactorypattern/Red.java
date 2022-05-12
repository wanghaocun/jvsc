package com.example.designpatterns.creational.abstractfactorypattern;

/**
 * @author wanghaocun
 * @since 2022-05-02
 **/
public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }

}
