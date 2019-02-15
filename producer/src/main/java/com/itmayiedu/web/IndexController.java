package com.itmayiedu.web;

import com.itmayiedu.dto.User;
import com.itmayiedu.service.FanoutProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private FanoutProducer fanoutProducer;

    @RequestMapping("/send")
    public void send(String username,String password){
        User user = new User(username, password);
        fanoutProducer.send(user);
    }
}
