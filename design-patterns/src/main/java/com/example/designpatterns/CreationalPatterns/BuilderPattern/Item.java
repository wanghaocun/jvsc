package com.example.designpatterns.CreationalPatterns.BuilderPattern;

/**
 * @author wanghaocun
 * @since 2022-05-04
 */
public interface Item {
    String name();

    Packing packing();

    float price();
}
