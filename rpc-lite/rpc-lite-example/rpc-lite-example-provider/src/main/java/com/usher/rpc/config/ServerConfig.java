package com.usher.rpc.config;

import com.usher.rpc.connector.RpcServer;
import com.usher.rpc.serialization.JacksonSerializor;
import com.usher.rpc.serialization.ProtobuffSerializor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {
    @Bean
    public RpcServer rpcServer(){
        RpcServer rpcServer = new RpcServer();
        rpcServer.setPort(8787);
        rpcServer.setSerializor(new JacksonSerializor());
        return rpcServer;
    }
}
