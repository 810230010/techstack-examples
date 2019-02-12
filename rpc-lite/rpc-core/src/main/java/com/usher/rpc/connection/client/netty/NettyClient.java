package com.usher.rpc.connection.client.netty;

import com.sun.xml.internal.bind.v2.TODO;
import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connection.AbstractNetcomClient;
import com.usher.rpc.connection.ConnectManager;
import com.usher.rpc.connection.server.netty.codec.RpcDecoder;
import com.usher.rpc.connection.server.netty.codec.RpcEncoder;
import com.usher.rpc.serializor.Serializor;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.util.concurrent.CountDownLatch;

public class NettyClient extends AbstractNetcomClient {
    private Bootstrap clientBootstrap;
    private ChannelFuture channelFuture;
    private NettyClientHandler nettyClientHandler;
    public NettyClient(String _serverAddress, int _serverPort, Serializor _serializor) {
        super(_serverAddress, _serverPort, _serializor);
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
                        socketChannel.pipeline().addLast(new RpcEncoder(RpcRequest.class, serializor))
//                                                .addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0))
                                .addLast(new RpcDecoder(RpcResponse.class, serializor))
                                .addLast(new NettyClientHandler());
                    }
                });
        ChannelFuture channelFuture = null;
        try {
            channelFuture = clientBootstrap.connect(serverAddress, serverPort).sync();
            Channel connect = channelFuture.channel();
            ConnectManager.storeClientConnect(serverAddress, connect);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //TODO 心跳检测、空闲检测、
    @Override
    public RpcResponse sendRequest(RpcRequest rpcRequest) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        NettyClientHandler clientHandler = new NettyClientHandler();
        this.nettyClientHandler = clientHandler;
        clientBootstrap = new Bootstrap();
        clientBootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new RpcEncoder(RpcRequest.class, serializor))
//                                                .addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0))
                                .addLast(new RpcDecoder(RpcResponse.class, serializor))
                                .addLast(nettyClientHandler);
                    }
                });

        try {
            channelFuture = clientBootstrap.connect(serverAddress, serverPort).sync();
            channelFuture.channel().writeAndFlush(rpcRequest).addListener(future -> {
               if(future.isSuccess()){
                   System.out.println();
               }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return nettyClientHandler.getResult();
    }
}
