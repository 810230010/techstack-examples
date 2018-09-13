package com.usher.rpc.controller;

import com.usher.iface.UserService;
import com.usher.rpc.stub.RpcClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RpcClientProxy<UserService> userServiceRpcClientProxy;
    @RequestMapping("/say")
    public Object say(){
        UserService userService = userServiceRpcClientProxy.getProxy();
        return userService.say();
    }
}
