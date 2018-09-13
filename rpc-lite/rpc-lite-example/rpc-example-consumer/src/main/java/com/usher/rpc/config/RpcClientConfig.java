package com.usher.rpc.config;

import com.usher.iface.UserService;
import com.usher.rpc.connection.jetty.client.JettyClient;
import com.usher.rpc.serializor.hessian.HessianSerializor;
import com.usher.rpc.stub.RpcClientProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcClientConfig {
    @Bean
    public RpcClientProxy<UserService> rpcClientProxy(){
        RpcClientProxy<UserService> userService = new RpcClientProxy<>();
        userService.setClassName(UserService.class.getName());
        userService.setClient(new JettyClient());
        userService.setIface(UserService.class);
        userService.setServerAddress("localhost");
        userService.setPort(8787);
        userService.setSerializor(new HessianSerializor());
        return userService;
    }
}
