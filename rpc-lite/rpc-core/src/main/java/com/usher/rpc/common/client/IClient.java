package com.usher.rpc.common.client;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.common.serialization.Serializor;

public abstract class IClient {
    protected Serializor serializor;
    protected String serverAddress;
    protected int port;

    public void init(String serverAddress, int port, Serializor serializor) {
        this.serverAddress = serverAddress;
        this.port = port;
        this.serializor = serializor;
    }

    public abstract RpcResponse sendRequest(RpcRequest rpcRequest);
}
