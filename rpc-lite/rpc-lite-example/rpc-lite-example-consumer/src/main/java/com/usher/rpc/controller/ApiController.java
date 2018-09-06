package com.usher.rpc.controller;

import com.user.rpc.api.IUserService;
import com.usher.rpc.connector.RpcClientProxy;
import com.usher.rpc.connector.jetty.client.JettyClient;
import com.usher.rpc.serialization.HessianSerializor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @Autowired
    private RpcClientProxy<IUserService> rpcClientProxy;

    @RequestMapping("/say")
    public String sayHello() {
        IUserService userService = null;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            userService = rpcClientProxy.getObject(IUserService.class);
            String word = userService.sayHi();
            stopWatch.stop();
            System.out.println(stopWatch.getTotalTimeMillis());
            return word;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
