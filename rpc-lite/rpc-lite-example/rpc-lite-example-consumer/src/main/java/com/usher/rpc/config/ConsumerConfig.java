package com.usher.rpc.config;

import com.user.rpc.api.IUserService;
import com.usher.rpc.connector.RpcClientProxy;
import com.usher.rpc.connector.jetty.client.JettyClient;
import com.usher.rpc.serialization.HessianSerializor;
import com.usher.rpc.serialization.ProtobuffSerializor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {
    @Bean
    public RpcClientProxy rpcClientProxy() {
        RpcClientProxy<IUserService> rpcClientProxy = new RpcClientProxy<>(new Class[]{IUserService.class}, "localhost", 8787, new ProtobuffSerializor<>(), new JettyClient());
        return rpcClientProxy;
    }
}
