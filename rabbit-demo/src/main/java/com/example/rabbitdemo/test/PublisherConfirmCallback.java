package com.example.rabbitdemo.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * @author wanghc
 * @since 2021-03-23
 **/
@Slf4j
@Configuration
public class PublisherConfirmCallback implements RabbitTemplate.ConfirmCallback {

    /**
     * Confirmation callback.
     *
     * @param correlationData correlation data for the callback.
     * @param ack             true for ack, false for nack
     * @param cause           An optional cause, for nack, when available, otherwise null.
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        //根据回调的correlationData的Id判断发送的消息投递（exchange）成功或失败
        String correlationId = correlationData != null ? correlationData.getId() : null;
        if (ack) {
            log.info("CONFIRM机制:消息投递成功:correlationId:{}", correlationId);
        } else {
            //TODO 消息投递失败,重新投递或记录

            log.warn("CONFIRM机制:消息投递失败：correlationId: {}, cause: {}", correlationId, cause);
        }

    }
}
