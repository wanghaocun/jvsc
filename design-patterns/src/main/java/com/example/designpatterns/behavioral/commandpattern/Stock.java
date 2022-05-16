package com.example.designpatterns.behavioral.commandpattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wanghc
 */
@Slf4j
public class Stock {

    private static final String NAME = "ABC";
    private static final int QUANTITY = 10;

    public void buy() {
        log.info("Stock [ Name: " + NAME + ",Quantity:" + QUANTITY + " ]bought ");
    }

    public void sell() {
        log.info("Stock [ Name: " + NAME + ",Quantity:" + QUANTITY + " ]sold ");
    }

}
