package com.itmayiedu.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class RabbitConfig {

    private static final String EXCHANGE_NAME = "springboot-rabbitmq-test-exchange";
    private static final String QUEUE_NAME = "springboot-rabbit-point2point-queue";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME);
    }


//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
//        return template;
//    }
}
