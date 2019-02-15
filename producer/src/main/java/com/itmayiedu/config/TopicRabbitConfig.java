package com.itmayiedu.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    private static final String EMAIL_QUEUE = "email_queue";
    private static final String MESSAGE_QUEUE = "message_queue";
    private static final String EXCHANGE_NAME = "topic_exchange";


    @Bean
    public Queue emailQueue(){
        return new Queue(EMAIL_QUEUE);
    }

    @Bean
    public Queue messageQueue(){
        return new Queue(MESSAGE_QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding bindingExchangeEmailQueue(Queue emailQueue, TopicExchange exchange){
        return BindingBuilder.bind(emailQueue).to(exchange).with("#.email");
    }

    @Bean
    Binding bindingExchangeMessageQueue(Queue messageQueue, TopicExchange exchange){
        return BindingBuilder.bind(messageQueue).to(exchange).with("#.message");
    }
}
