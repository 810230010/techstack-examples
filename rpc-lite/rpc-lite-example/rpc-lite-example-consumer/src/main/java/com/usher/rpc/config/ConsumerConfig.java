package com.usher.rpc.config;

import com.user.rpc.api.IUserService;
import com.usher.rpc.connector.RpcClientProxy;
import com.usher.rpc.serialization.JacksonSerializor;
import com.usher.rpc.serialization.ProtobuffSerializor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConsumerConfig {
    public RpcClientProxy rpcClientProxy(){
        RpcClientProxy<IUserService> rpcClientProxy = new RpcClientProxy.Builder().iface(IUserService.class)
                .port(8787).serverAddress("localhost").serializor(new JacksonSerializor())
                .build();
        return rpcClientProxy;
    }
}
