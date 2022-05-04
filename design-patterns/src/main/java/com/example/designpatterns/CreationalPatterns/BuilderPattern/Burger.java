package com.example.designpatterns.CreationalPatterns.BuilderPattern;

/**
 * @author wanghaocun
 * @since 2022-05-04
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
