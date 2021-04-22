package com.example.rabbitdemo.test;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wanghc
 * @since 2021-03-22
 **/
@Slf4j
@Component
public class Consumer {

    @RabbitHandler
    @RabbitListener(queues = "${mq.subscribe.queue}")
    public void process(String content, Channel channel, Message message) {
        log.info("收到消息：{}", content);

        // TODO: 2021/3/23 business process

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            channel.basicAck(deliveryTag, false);
            log.info("消息手动ACK完成");
        } catch (IOException e) {
            log.error("消息处理/手动ACK失败：{}", e.getMessage(), e);
            try {
                if (message.getMessageProperties().getRedelivered()) {
                    log.warn("消息重复处理失败，拒绝再次接受");
                    channel.basicReject(deliveryTag, false);
                } else {
                    log.warn("消息将再次入队进行处理");
                    channel.basicNack(deliveryTag, false, true);
                }
            } catch (IOException ioException) {
                log.error("消息补偿处理失败：{}", e.getMessage(), e);
            }
        }

    }

}
