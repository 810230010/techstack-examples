package com.usher.rpc.config;

import com.usher.rpc.connection.jetty.server.JettyServer;
import com.usher.rpc.connection.netty.server.NettyServer;
import com.usher.rpc.registry.zookeeper.ZKServiceRegister;
import com.usher.rpc.serializor.hessian.HessianSerializor;
import com.usher.rpc.stub.RpcServerInvoker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProviderConfig {
    @Bean
    public RpcServerInvoker rpcServerInvoker(){
        RpcServerInvoker rpcServerInvoker = new RpcServerInvoker();
        rpcServerInvoker.setPort(8787);
        rpcServerInvoker.setSerializor(new HessianSerializor());
        rpcServerInvoker.setServer(new NettyServer());
        rpcServerInvoker.setServiceRegister(new ZKServiceRegister("localhost:2181", 20880));
        return rpcServerInvoker;
    }
}
