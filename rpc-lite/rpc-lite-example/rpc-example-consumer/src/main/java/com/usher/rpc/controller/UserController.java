package com.usher.rpc.controller;

import com.usher.iface.DemoService;
import com.usher.iface.UserService;
import com.usher.rpc.config.RpcReferenceConfig;
import com.usher.rpc.stub.RpcClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
