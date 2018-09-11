package com.usher.rpc.connection;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.serializor.Serializor;

public abstract class IClient {
    protected String serverAddress;
    protected int serverPort;
    protected Serializor serializor;
    public void init(String serverAddress, int serverPort, Serializor serializor){
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.serializor = serializor;
    }

    public abstract RpcResponse sendRequest(RpcRequest rpcRequest);
}
