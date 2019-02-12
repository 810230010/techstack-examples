package com.usher.rpc.connection.server.netty;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connection.AbstractNetcomServer;
import com.usher.rpc.connection.server.netty.codec.RpcDecoder;
import com.usher.rpc.connection.server.netty.codec.RpcEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyNetcomServer extends AbstractNetcomServer {
    @Override
    public void startServer() {
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
                log.info("netty server start up at port:{}", listenPort);
                future.channel().closeFuture().sync().channel();
            } catch (InterruptedException e) {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
                e.printStackTrace();
            }finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
            }
    }

    @Override
    public void stopServer() {

    }

//    @Override
//    public void stopServer() {
//        thread.interrupt();
//    }
}
