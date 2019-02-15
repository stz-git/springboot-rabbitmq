package com.itmayiedu.service;

import com.alibaba.fastjson.JSON;
import com.itmayiedu.dto.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FanoutProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private static final String QUEUE_NAME = "springboot-rabbit-point2point-queue";
    private static final String EXCHANGE_NAME = "topic_exchange";
    private static final String FANOUT_EXCHANGE_NAME = "fanout_exchange";

    public void send(User user) {
        System.out.println("message: " + user);
        Message message = MessageBuilder.withBody(JSON.toJSONString(user).getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("UTF-8")
                .setMessageId(UUID.randomUUID().toString()).build();
        amqpTemplate.convertAndSend(QUEUE_NAME, message);
    }

    public void sendEmail() {
        User email = new User("email", "123");
        System.out.println("send: " + email.toString());
        amqpTemplate.convertAndSend(EXCHANGE_NAME, "1.1.email", email);
    }

    public void sendMessage() {
        User message = new User("message", "123");
        amqpTemplate.convertAndSend(EXCHANGE_NAME, "1.message", message);
    }

    public void send2fanout() {
        User aPublic = new User("public", "123");
        amqpTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "",aPublic);
    }
}
