package com.usher.rpc.config;

import com.usher.iface.UserService;
import com.usher.rpc.connection.jetty.client.JettyClient;
import com.usher.rpc.connection.netty.client.NettyClient;
import com.usher.rpc.registry.zookeeper.ZKServiceRegister;
import com.usher.rpc.serializor.hessian.HessianSerializor;
import com.usher.rpc.stub.RpcClientProxy;
import com.usher.rpc.stub.RpcClientRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcClientConfig {
    @Bean
    @Qualifier("aa")
    public RpcClientProxy<UserService> rpcClientProxy(){
        RpcClientProxy<UserService> userService = new RpcClientProxy<>();
        userService.setClassName(UserService.class.getName());
        userService.setIface(UserService.class);
        userService.setRpcClientRegistry(rpcClientRegistry());
        return userService;
    }
    @Bean
    public RpcClientRegistry rpcClientRegistry(){
        RpcClientRegistry rpcClientRegistry = new RpcClientRegistry();
        rpcClientRegistry.setServerAddress("localhost");
        rpcClientRegistry.setPort(8787);
        rpcClientRegistry.setSerializor(new HessianSerializor());
        rpcClientRegistry.setClient(new NettyClient());
        rpcClientRegistry.setServiceRegister(new ZKServiceRegister("localhost", 2181));
        return rpcClientRegistry;
    }
}
