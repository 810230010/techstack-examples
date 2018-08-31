package com.usher.rpc.config;

import com.user.rpc.api.IUserService;
import com.usher.rpc.connector.RpcClientProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConsumerConfig {
    public RpcClientProxy rpcClientProxy(){
        RpcClientProxy rpcClientProxy =  new RpcClientProxy(IUserService.class, "localhost", 8787);
        return rpcClientProxy;
    }
}
