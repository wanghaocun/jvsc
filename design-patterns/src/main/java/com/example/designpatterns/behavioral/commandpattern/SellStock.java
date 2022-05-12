package com.example.designpatterns.behavioral.commandpattern;

/**
 * @author wanghc
 */
public class SellStock implements Order {

    private final Stock abcStock;

    public SellStock(Stock abcStock) {
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.sell();
    }

}
