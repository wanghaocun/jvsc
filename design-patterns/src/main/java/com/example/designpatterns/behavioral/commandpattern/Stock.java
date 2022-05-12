package com.example.designpatterns.behavioral.commandpattern;

/**
 * @author wanghc
 */
public class Stock {

    private final String name = "ABC";
    private final int quantity = 10;

    public void buy() {
        System.out.println("Stock [ Name: " + name + ",Quantity:" + quantity + " ]bought ");
    }

    public void sell() {
        System.out.println("Stock [ Name: " + name + ",Quantity:" + quantity + " ]sold ");
    }

}
