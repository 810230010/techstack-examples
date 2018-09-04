package com.usher.rpc.controller;

import com.user.rpc.api.IUserService;
import com.usher.rpc.connector.RpcClientProxy;
import com.usher.rpc.serialization.HessianSerializor;
import com.usher.rpc.serialization.JacksonSerializor;
import com.usher.rpc.serialization.ProtobuffSerializor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.util.resources.TimeZoneNames;

import javax.annotation.Resource;

@RestController
public class ApiController {

    @RequestMapping("/say")
    public String sayHello(){
        IUserService userService = null;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            RpcClientProxy<IUserService> rpcClientProxy =  new RpcClientProxy.Builder().iface(IUserService.class)
                    .port(8787).serverAddress("localhost").serializor(new ProtobuffSerializor())
                    .build();
            userService = rpcClientProxy.getObject();
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
