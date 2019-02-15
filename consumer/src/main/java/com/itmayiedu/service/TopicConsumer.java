package com.itmayiedu.service;

import com.itmayiedu.dto.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

//    @RabbitListener(queues = "email_queue")
    public void email(User user) {
        System.out.println("email: " + user.getUsername());
    }

//    @RabbitListener(queues = "message_queue")
    public void message(User user) {
        System.out.println("message: " + user.getUsername());
    }
}
