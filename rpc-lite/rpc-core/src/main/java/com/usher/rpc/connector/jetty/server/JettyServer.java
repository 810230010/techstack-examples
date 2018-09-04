package com.usher.rpc.connector.jetty.server;

import com.usher.rpc.common.server.IServer;
import com.usher.rpc.serialization.Serializor;

public class JettyServer extends IServer {
    @Override
    public void startServer(int port, Serializor serializor){
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        serverThread.setDaemon(true);
        serverThread.start();
    }
}
