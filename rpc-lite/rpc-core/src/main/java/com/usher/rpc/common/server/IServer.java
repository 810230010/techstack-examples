package com.usher.rpc.common.server;

import com.usher.rpc.serialization.Serializor;

public abstract class IServer {
    protected int port;
    public abstract void startServer(int port, Serializor serializor);
}
