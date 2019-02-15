package com.itmayiedu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SingleConsumer {

    private static int tianyuCount = 0;

    @RabbitListener(queues = "springboot-rabbit-point2point-queue", containerFactory = "rabbitListenerContainerFactory")
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        int num = 1;
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);//sign this msg
        try {
            String messageId = message.getMessageProperties().getMessageId();//find messageId in database
            String s = new String(message.getBody(), "UTF-8");
            JSONObject jsonObject = JSON.parseObject(s);
            if (jsonObject.getString("username").equals("tianyu"))
                num = 0;
            System.out.println(s);
            int i = 2 / num;
            System.out.println("result: " + i);

            //success,send ACK to MQServer

            channel.basicAck(deliveryTag, false);

        } catch (Exception e) {
            channel.basicReject(deliveryTag,false);// reject this message and requeue is false, enter dead queue
            throw new IllegalArgumentException("error by Zero");
        }
    }
}

