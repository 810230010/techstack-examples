package com.usher.rpc.connection;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.serializor.Serializor;
import com.usher.rpc.stub.RpcClientRegistry;

public abstract class AbstractNetcomClient {
    protected String serverAddress;
    protected int serverPort;
    protected Serializor serializor;
    public AbstractNetcomClient(String _serverAddress, int _serverPort, Serializor _serializor){
        this.serverAddress = _serverAddress;
        this.serverPort = _serverPort;
        this.serializor = _serializor;
    }
    public void init(RpcClientRegistry rpcClientRegistry){
        this.serverAddress = rpcClientRegistry.getServerAddress();
        this.serverPort = rpcClientRegistry.getPort();
        this.serializor = rpcClientRegistry.getSerializor();
    }

    public abstract RpcResponse sendRequest(RpcRequest rpcRequest);
}
