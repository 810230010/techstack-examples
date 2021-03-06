package com.usher.rpc.connection;

import com.usher.rpc.serializor.Serializor;

public abstract class AbstractNetcomServer {
    protected int listenPort;
    protected Serializor serializor;
    public AbstractNetcomServer init(int port, Serializor serializor){
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
