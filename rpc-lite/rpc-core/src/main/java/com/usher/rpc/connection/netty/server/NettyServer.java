package com.usher.rpc.connection.netty.server;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connection.AbstractServer;
import com.usher.rpc.connection.netty.codec.RpcDecoder;
import com.usher.rpc.connection.netty.codec.RpcEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer extends AbstractServer {
    private Thread thread;
    @Override
    public void startServer() {
        thread = new Thread(()->{
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
//                                    .addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0))
                                                    .addLast(new RpcDecoder(RpcRequest.class, serializor))
                                                    .addLast(new RpcEncoder(RpcResponse.class, serializor))
                                                    .addLast(new NettyServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            try {
                ChannelFuture future = serverBootstrap.bind(listenPort).sync();
                future.channel().closeFuture().sync().channel();
            } catch (InterruptedException e) {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
                e.printStackTrace();
            }finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
            }
        });
        thread.setDaemon(true);
        thread.start();

    }

    @Override
    public void stopServer() {
        thread.interrupt();
    }
}
