package com.usher.rpc.controller;

import com.usher.iface.UserService;
import com.usher.rpc.stub.RpcClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("aa")
    private RpcClientProxy<UserService> proxy1;
    @RequestMapping("/say")
    public Object say(){
        UserService userService = proxy1.getProxy();
        return userService.say();
    }
}
