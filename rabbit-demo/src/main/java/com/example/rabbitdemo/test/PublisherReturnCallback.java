package com.example.rabbitdemo.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNullApi;

/**
 * @author wanghc
 * @since 2021-03-23
 **/
@Slf4j
@Configuration
public class PublisherReturnCallback implements RabbitTemplate.ReturnCallback {

    /**
     * Returned message callback.
     *
     * @param message    the returned message.
     * @param replyCode  the reply code.
     * @param replyText  the reply text.
     * @param exchange   the exchange.
     * @param routingKey the routing key.
     */
    @Override
    public void returnedMessage(Message message, int replyCode,
                                String replyText, String exchange, String routingKey) {

        String msg = new String(message.getBody());
        log.error("RETURN机制:消息路由绑定失败：msg: {}, replyCode: {}, replyText: {}, exchange: {}, routingKey: {}",
                msg, replyCode, replyText, exchange, routingKey);
        // TODO: 2021/3/23 记录路由失败记录

    }

}
