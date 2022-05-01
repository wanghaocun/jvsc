package com.example.server.controller;

import com.example.server.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wanghc
 * @since 2022-01-18
 **/
@Component
public class RpcServer {

    @Resource
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitConfig.RPC_MSG_QUEUE)
    public void process(Message message) {
        byte[] body = message.getBody();
        Message build =
                MessageBuilder.withBody(("我是服务端，我收到了客户端发来的消息：%s".formatted(new String(body))).getBytes()).build();
        CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
        rabbitTemplate.sendAndReceive(RabbitConfig.RPC_EXCHANGE, RabbitConfig.RPC_REPLY_MSG_QUEUE, build,
                correlationData);
    }
}
