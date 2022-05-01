package com.example.rabbitdemo.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author wanghc
 * @since 2021-03-22
 **/
@Slf4j
@Component
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(Object content) {

        String id = UUID.randomUUID().toString();
        CorrelationData correlationData = new CorrelationData(id);
//        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, content, correlationData);

        // 自定义请求头信息
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setContentType("application/json");
            message.getMessageProperties().setContentEncoding("UTF-8");
            message.getMessageProperties().setCorrelationId(id);

            message.getMessageProperties().setHeader("lang", "py");
            message.getMessageProperties().setHeader("task", "test_a_add_b");
            message.getMessageProperties().setHeader("id", id);
            message.getMessageProperties().setHeader("root_id", id);
            message.getMessageProperties().setHeader("parent_id", id);
            message.getMessageProperties().setHeader("group", id);

            return message;
        };

        rabbitTemplate.convertAndSend("lz-file-parsing", "source-document-routing1", content, messagePostProcessor);
        log.info("消息发送完成，correlationData: {}", correlationData);
    }

}
