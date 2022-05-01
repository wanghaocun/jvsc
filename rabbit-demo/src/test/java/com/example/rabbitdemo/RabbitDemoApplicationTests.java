package com.example.rabbitdemo;

import com.example.rabbitdemo.test.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.UUID;

@SpringBootTest
class RabbitDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private Producer producer;
    @Autowired
    private RabbitTemplate template;

    @Test
    public void test1() {

        HashMap<Object, Object> msg = new HashMap<>();
        msg.put("namea", 123);
        msg.put("nameb", 456);

        HashMap<Object, Object> tail = new HashMap<>();
        tail.put("callbacks", null);
        tail.put("errbacks", null);
        tail.put("chain", null);
        tail.put("chord", null);

        Object[] s = new Object[3];

        s[0] = new Object[]{};
        s[1] = msg;
        s[2] = tail;


        producer.publish(s);

        System.out.println("======================end");
    }

    @Test
    public void test() throws JsonProcessingException {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");

        Object[] s = {"1", "2", "3"};

        ObjectMapper objectMapper = new ObjectMapper();
        String msg = objectMapper.writeValueAsString(s);

        String id = UUID.randomUUID().toString().replace("-", "");

        MessageProperties properties = MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setCorrelationId(id)
                .setDeliveryMode(MessageDeliveryMode.fromInt(2))
                .setHeader("id", id)
                .setHeader("root_id", id)
                .setHeader("argsrepr", "(1,2,3)")
                .build();


        Message message = MessageBuilder.withBody(msg.getBytes(StandardCharsets.UTF_8))
                .andProperties(properties).build();

        template.setExchange("RabbitConfig.EXCHANGE");
        template.setRoutingKey("RabbitConfig.ROUTING_KEY");
        template.send(message);

//        for (int i = 0; i < 1; i++) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            String ss = objectMapper.writeValueAsString(s);
//            producer.publish(ss);
//            producer.publish("hello rabbit");
//        }

    }

}
