package com.usher.rpc.connection.netty.client;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connection.AbstractClient;
import com.usher.rpc.connection.netty.codec.RpcDecoder;
import com.usher.rpc.connection.netty.codec.RpcEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class NettyClient extends AbstractClient {
    @Override
    public RpcResponse sendRequest(RpcRequest rpcRequest) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap clientBootstrap = new Bootstrap();
        RpcResponse response = new RpcResponse();
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
        try {
            ChannelFuture channelFuture = clientBootstrap.connect(serverAddress, serverPort);
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(channelFuture.isSuccess()){
                        System.out.println("client connect future state: " + channelFuture.isSuccess());
                        ChannelFuture channelFuture1 = channelFuture.channel().writeAndFlush(rpcRequest);
                        channelFuture1.addListener(new ChannelFutureListener() {
                            @Override
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                if(channelFuture.isDone()){
                                    System.out.println("完成~");
                                }

                            }
                        });
                    }
                }
            });

            try {
                channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RpcResponse response1 = (RpcResponse) channelFuture.channel().attr(AttributeKey.valueOf("RPC-RESPONSE")).get();
            System.out.println(response1.getResult());

            return response1;
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
