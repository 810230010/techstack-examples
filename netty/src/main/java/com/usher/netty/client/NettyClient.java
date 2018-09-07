package com.usher.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class NettyClient {
    private String serverAddress;
    private int port;
    public NettyClient(String serverAddress, int port){
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void startClient(){
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap clientBootstrap = new Bootstrap();
        clientBootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = clientBootstrap.connect(serverAddress, port).sync();
            // Wait until the connection is closed.
            channelFuture.channel().closeFuture().sync();
            String msg = (String)channelFuture.channel().attr(AttributeKey.valueOf("msg")).get();
            System.out.println("哇， 我从client handler里获取到了信息: " + msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyClient client = new NettyClient("localhost", 8989);
        client.startClient();
    }
}
