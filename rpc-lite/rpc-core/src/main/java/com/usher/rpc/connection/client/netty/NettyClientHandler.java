package com.usher.rpc.connection.client.netty;

import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connection.RpcFutureManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class NettyClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
    private static final Logger logger = LogManager.getLogger(NettyClientHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {
        RpcFutureManager futureManager = RpcFutureManager.getInstance();
        futureManager.notifyFutureResponse(rpcResponse.getRequestId(), rpcResponse);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
