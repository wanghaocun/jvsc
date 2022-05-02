package com.example.designpatterns.CreationalPatterns.FactoryPatterns;

import lombok.Data;

/**
 * @author wanghaocun
 * @since 2022-05-01
 **/
@Data
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }

}
