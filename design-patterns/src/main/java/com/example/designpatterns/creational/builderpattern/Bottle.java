package com.example.designpatterns.creational.builderpattern;

/**
 * @author wanghaocun
 * @since 2022-05-04
 */
public class Bottle implements Packing {

    @Override
    public String pack() {
        return "Bottle";
    }
}
