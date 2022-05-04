package com.example.designpatterns.CreationalPatterns.BuilderPattern;

/**
 * @author wanghaocun
 * @since 2022-05-04
 */
public class Wrapper implements Packing {

    @Override
    public String pack() {
        return "Wrapper";
    }
}
