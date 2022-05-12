package com.example.designpatterns.behavioral.commandpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghc
 */
public class Broker {

    private final List<Order> orderList = new ArrayList<>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }

}
