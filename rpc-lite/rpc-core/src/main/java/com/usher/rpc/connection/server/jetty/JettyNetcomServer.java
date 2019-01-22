package com.usher.rpc.connection.server.jetty;

import com.usher.rpc.connection.AbstractNetcomServer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;


@Slf4j
public class JettyNetcomServer extends AbstractNetcomServer {
    private Server server;

    @Override
    public void startServer() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                server = new Server(listenPort);
                // 非阻塞

                HandlerCollection handlerCollection = new HandlerCollection();
                handlerCollection.setHandlers(new Handler[]{new JettyServerHandler(serializor)});
                server.setHandler(handlerCollection);
                try {
                    if(log.isInfoEnabled()){
                        log.info("-----------embedded jetty server start up at port: {}", listenPort);
                    }
                    server.start();

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
