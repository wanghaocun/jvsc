package com.example.rabbitdemo.test;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wanghc
 * @since 2021-03-22
 **/
@Configuration
public class RabbitConfig {

    private final PublisherConfirmCallback confirmCallback;
    private final PublisherReturnCallback returnCallback;
    private final CachingConnectionFactory connectionFactory;
    private final SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    @Value("${mq.exchange}")
    String parsingExchange;

    @Value("${mq.publish.routing}")
    String sourceRoutingKey;

    @Value("${mq.publish.queue}")
    String sourceQueue;

    @Value("${mq.subscribe.routing}")
    String resultRoutingKey;

    @Value("${mq.subscribe.queue}")
    String resultQueue;

    public RabbitConfig(PublisherConfirmCallback confirmCallback,
                        PublisherReturnCallback returnCallback,
                        CachingConnectionFactory connectionFactory,
                        SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer) {
        this.confirmCallback = confirmCallback;
        this.returnCallback = returnCallback;
        this.connectionFactory = connectionFactory;
        this.factoryConfigurer = factoryConfigurer;
    }

    @Bean("PARSING_EXCHANGE")
    public DirectExchange parsingExchange() {
        return new DirectExchange(parsingExchange);
    }

    /**
     * 相同bean 多实例化
     */
    @Bean("SOURCE_QUEUE")
    public Queue sourceQueue() {
        return new Queue(sourceQueue);
    }

    /**
     * 相同bean 多实例化
     */
    @Bean("RESULT_QUEUE")
    public Queue resultQueue() {
        return new Queue(resultQueue);
    }

    @Bean
    public Binding sourceBinding() {
        return BindingBuilder.bind(sourceQueue()).to(parsingExchange()).with(sourceRoutingKey);
    }

    @Bean
    public Binding resultBinding() {
        return BindingBuilder.bind(resultQueue()).to(parsingExchange()).with(resultRoutingKey);
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory listenerContainerFactory() {
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();

        containerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        factoryConfigurer.configure(containerFactory, connectionFactory);

        return containerFactory;
    }

}
