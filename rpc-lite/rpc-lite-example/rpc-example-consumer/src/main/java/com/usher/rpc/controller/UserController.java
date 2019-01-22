package com.usher.rpc.controller;

import com.usher.iface.DemoService;
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
        DemoService demoService = (DemoService) applicationContext.getBean("demoService");
        return demoService.say();
    }
}
