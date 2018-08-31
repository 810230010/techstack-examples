package com.usher.rpc.controller;

import com.user.rpc.api.IUserService;
import com.usher.rpc.connector.RpcClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ApiController {

    @RequestMapping("/say")
    public String sayHello(){
        IUserService userService = null;
        try {
            RpcClientProxy<IUserService> rpcClientProxy =  new RpcClientProxy(IUserService.class, "localhost", 8787);
            userService = rpcClientProxy.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userService.sayHi();
    }
}
