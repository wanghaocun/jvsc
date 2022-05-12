package com.example.designpatterns.creational.builderpattern;

/**
 * @author wanghaocun
 * @since 2022-05-04
 */
public class Coke extends ColdDrink {

    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}
