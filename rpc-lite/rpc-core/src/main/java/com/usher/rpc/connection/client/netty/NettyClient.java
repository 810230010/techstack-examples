package com.usher.rpc.connection.client.netty;

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

public class NettyClient extends AbstractNetcomClient {
    private Bootstrap clientBootstrap;
    private Channel channel;
    private EventLoopGroup eventLoopGroup;
    public NettyClient(String _serverAddress, int _serverPort, Serializor _serializor) {
        super(_serverAddress, _serverPort, _serializor);
    }

    public void connect() {
        this.eventLoopGroup = new NioEventLoopGroup();
        Bootstrap clientBootstrap = new Bootstrap();
        clientBootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    // TODO 心跳检测、空闲检测、 拆包
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //
                        socketChannel.pipeline().addLast(new RpcEncoder(RpcRequest.class, serializor))
//                                                .addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0))
                                .addLast(new RpcDecoder(RpcResponse.class, serializor))
                                .addLast(new NettyClientHandler());
                    }
                });
        ChannelFuture channelFuture = null;
        try {
            channelFuture = clientBootstrap.connect(serverAddress, serverPort).sync();
            this.channel = channelFuture.channel();
            ConnectManager.storeClientConnect(serverAddress, channel);
            if(!isValidConnect()){
                close();
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private boolean isValidConnect() {
        if(null != this.channel){
            return this.channel.isActive();
        }
        return false;
    }


    public void close(){
        if(this.channel != null && this.channel.isActive()){
            this.channel.close();
        }
        if(this.eventLoopGroup != null && !this.eventLoopGroup.isShutdown()){
            this.eventLoopGroup.shutdownGracefully();
        }
    }

    //TODO 结果返回方式: 同步、future、callback
    @Override
    public RpcResponse sendRequest(RpcRequest rpcRequest) {
        try {
            if(!isValidConnect()){
                connect();
            }
            this.channel.writeAndFlush(rpcRequest).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
