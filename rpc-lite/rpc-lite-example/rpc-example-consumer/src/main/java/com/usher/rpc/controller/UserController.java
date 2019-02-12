package com.usher.rpc.controller;

import com.usher.iface.DemoService;
import com.usher.iface.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/test")
    public String test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("rpc-consumer.xml");
        applicationContext.start();
        UserService userService = (UserService) applicationContext.getBean("userService");
        return userService.say();
    }
}
