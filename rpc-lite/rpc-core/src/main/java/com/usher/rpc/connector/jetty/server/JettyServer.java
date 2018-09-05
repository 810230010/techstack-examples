package com.usher.rpc.connector.jetty.server;

import com.usher.rpc.common.server.IServer;
import com.usher.rpc.common.serialization.Serializor;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;

public class JettyServer extends IServer {
    private Server server;
    @Override
    public void startServer(int port, Serializor serializor){
        Thread serverThread = new Thread(()-> {
           server = new Server();
           server.setThreadPool(new ExecutorThreadPool(200, 200, 30000));
            SelectChannelConnector selectChannelConnector = new SelectChannelConnector();
            selectChannelConnector.setPort(port);
            selectChannelConnector.setMaxIdleTime(30000);
            server.setConnectors(new Connector[]{selectChannelConnector});
            // handler
            HandlerCollection handlerc =new HandlerCollection();
            handlerc.setHandlers(new Handler[]{new JettyServerHandler(serializor)});
            server.setHandler(handlerc);
            try {
                server.start();
                server.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();
    }

    @Override
    public void destroy() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
