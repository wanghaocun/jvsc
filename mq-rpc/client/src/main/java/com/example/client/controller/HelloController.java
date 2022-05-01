package com.example.client.controller;

import com.example.client.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wanghc
 * @since 2022-01-18
 **/
@RestController
public class HelloController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void hello(String message) {
        Message msg = MessageBuilder.withBody(message.getBytes()).build();
        Message result = rabbitTemplate.sendAndReceive(RabbitConfig.RPC_EXCHANGE, RabbitConfig.RPC_MSG_QUEUE, msg);
        if (result != null) {
            String correlationId = msg.getMessageProperties().getCorrelationId();
            String springReturnedMessageCorrelation = (String) result.getMessageProperties().getHeaders().get(
                    "spring_returned_message_correlation");
            if (correlationId.equals(springReturnedMessageCorrelation)) {
                System.out.printf("收到服务端响应：%s%n", new String(result.getBody()));
            }
        }
    }
}
