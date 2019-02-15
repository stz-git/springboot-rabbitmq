package com.itmayiedu.service;

import com.itmayiedu.dto.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FanoutConsumer {

    private static int retryCount = 0;

    @RabbitListener(queues = "a_queue")
    public void a_fanout(User user, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        System.out.println("a_fanout: " + user.getUsername());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicReject(deliveryTag, false);
    }

    @RabbitListener(queues = "b_queue")
    public void b_fanout(User user, @Headers Map<String, Object> headers, Channel channel) throws Exception{
        System.out.println("b_fanout: " + user.getUsername());
//        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
//        channel.basicReject(deliveryTag, false);
        throw new IllegalArgumentException("hahahahahaha");
    }

    @RabbitListener(queues = "dead_letter_queue_1")
    public void listenDeadQueue1(User user,@Headers Map<String, Object> headers, Channel channel) throws Exception{
        System.out.println("dead_letter_consumer1 Receive: " + user);
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag,false);
    }

    @RabbitListener(queues = "dead_letter_queue_2")
    public void listenDeadQueue2(User user,@Headers Map<String, Object> headers, Channel channel) throws Exception{
        System.out.println("dead_letter_consumer2 Receive: " + user);
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag,false);
    }
}
