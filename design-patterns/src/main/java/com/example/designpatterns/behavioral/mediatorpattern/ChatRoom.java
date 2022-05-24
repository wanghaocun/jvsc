package com.example.designpatterns.behavioral.mediatorpattern;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author wanghaocun
 */
@Slf4j
public class ChatRoom {

    private ChatRoom() {
        throw new IllegalStateException("Utility class");
    }

    public static void showMessage(User user, String message) {
        log.info(LocalDate.now() + " [" + user.getName() + "] : " + message);
    }

}
