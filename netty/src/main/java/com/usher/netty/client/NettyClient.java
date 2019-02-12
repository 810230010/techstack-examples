package com.usher.netty.client;

import com.usher.netty.codec.NettyDecoder;
import com.usher.netty.codec.NettyEncoder;
import com.usher.netty.entity.Result;
import com.usher.netty.entity.User;
import com.usher.netty.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClient {
    private String serverAddress;
    private int port;
    private Channel channel;
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
                        socketChannel.pipeline().addLast(new NettyEncoder(User.class))
                                                .addLast(new NettyDecoder(Result.class))
                                                .addLast(new NettyClientHandler());
                    }
                });
        ChannelFuture channelFuture = null;
        try {
            channelFuture = clientBootstrap.connect(serverAddress, port).sync();
            this.channel = channelFuture.channel();

            // Wait until the connection is closed.
//            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//            String msg = (String)channelFuture.channel().attr(AttributeKey.valueOf("msg")).get();
//            System.out.println("哇， 我从client handler里获取到了信息: " + msg);

    }

    public void connect(){
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap clientBootstrap = new Bootstrap();
        clientBootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyEncoder(User.class))
                                .addLast(new NettyDecoder(Result.class))
                                .addLast(new NettyClientHandler());
                    }
                });
        ChannelFuture channelFuture = null;
        try {
            channelFuture = clientBootstrap.connect(serverAddress, port).sync();
            Channel connect = channelFuture.channel();
            ConnectManager.storeClientConnect(serverAddress, connect);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void sendRequest(User user){
        Channel channel = ConnectManager.getConnect(serverAddress);
        channel.writeAndFlush(user);
    }


    public static void main(String[] args) {
        NettyClient client = new NettyClient("localhost", 8989);
        client.connect();
        User user = new User();
        user.setUsername("jjp");
        user.setPassword("123");
        client.sendRequest(user);
    }
}
