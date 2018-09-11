package com.usher.rpc.connection.netty.server;

import com.usher.rpc.codec.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<RpcRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcRequest request) throws Exception {

    }
}
