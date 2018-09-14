package com.usher.rpc.connection;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.serializor.Serializor;

public abstract class IServer {
    protected int listenPort;
    protected Serializor serializor;
    public IServer init(int port, Serializor serializor){
        this.listenPort = port;
        this.serializor = serializor;
        return this;
    }


    /**
     * 接受请求并触发接口方法
     */
    public abstract void startServer();
    public abstract void stopServer();
}
