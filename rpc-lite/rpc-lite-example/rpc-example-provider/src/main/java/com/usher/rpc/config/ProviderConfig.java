package com.usher.rpc.config;

import com.usher.rpc.connection.server.netty.NettyNetcomServer;
import com.usher.rpc.registry.zookeeper.ZKServiceRegister;
import com.usher.rpc.serializor.hessian.HessianSerializor;
import com.usher.rpc.stub.RpcServiceInvoker;

public class ProviderConfig {
    public RpcServiceInvoker rpcServerInvoker(){
        RpcServiceInvoker rpcServerInvoker = new RpcServiceInvoker();
//        rpcServerInvoker.setPort(8787);
//        rpcServerInvoker.setSerializor(new HessianSerializor());
//        rpcServerInvoker.setServer(new NettyNetcomServer());
//        rpcServerInvoker.setServiceRegister(new ZKServiceRegister("localhost:2181", 20880));
        return rpcServerInvoker;
    }
}
