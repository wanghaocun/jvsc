package com.example.designpatterns.behavioral.mediatorpattern;

import lombok.Data;

/**
 * @author wanghaocun
 */
@Data
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public void sendMessage(String message) {
        ChatRoom.showMessage(this, message);
    }

}
