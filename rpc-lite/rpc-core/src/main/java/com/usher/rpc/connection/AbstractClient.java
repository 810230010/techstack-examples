package com.usher.rpc.connection;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.serializor.Serializor;
import com.usher.rpc.stub.RpcClientRegistry;

public abstract class AbstractClient {
    protected String serverAddress;
    protected int serverPort;
    protected Serializor serializor;
    public void init(RpcClientRegistry rpcClientRegistry){
        this.serverAddress = rpcClientRegistry.getServerAddress();
        this.serverPort = rpcClientRegistry.getPort();
        this.serializor = rpcClientRegistry.getSerializor();
    }

    public abstract RpcResponse sendRequest(RpcRequest rpcRequest);
}
