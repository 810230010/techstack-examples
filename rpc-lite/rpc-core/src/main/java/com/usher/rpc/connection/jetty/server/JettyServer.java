package com.usher.rpc.connection.jetty.server;

import com.usher.rpc.connection.IServer;
import com.usher.rpc.serializor.Serializor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;

@Slf4j
public class JettyServer extends IServer {
    private Server server;

    @Override
    public void startServer() {
        server = new Server();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 非阻塞
                server.setThreadPool(new ExecutorThreadPool(200, 200, 30000));

                SelectChannelConnector connector = new SelectChannelConnector();
                connector.setMaxIdleTime(30000);
                connector.setPort(listenPort);
                server.setConnectors(new Connector[]{connector});

                HandlerCollection handlerCollection = new HandlerCollection();
                handlerCollection.setHandlers(new Handler[]{new JettyServerHandler(serializor)});
                server.setHandler(handlerCollection);

                try {
                    server.start();
                    if(log.isDebugEnabled()){
                        log.info("-----------server start up at port: {}", listenPort);
                    }
                    server.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void stopServer() {
        try {
            server.stop();
            if(log.isDebugEnabled()){
                log.debug("server stop...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
