package com.itmayiedu.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutProducerTest {

    @Autowired
    private FanoutProducer fanoutProducer;

    @Test
    public void sendEmail() {
        fanoutProducer.sendEmail();
    }

    @Test
    public void sendMessage() {
        fanoutProducer.sendMessage();
    }

    @Test
    public void setFanoutProducer(){
        fanoutProducer.send2fanout();
    }
}