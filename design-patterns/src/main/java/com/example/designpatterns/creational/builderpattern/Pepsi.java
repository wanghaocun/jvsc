package com.example.designpatterns.creational.builderpattern;

/**
 * @author wanghaocun
 * @since 2022-05-04
 */
public class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}
