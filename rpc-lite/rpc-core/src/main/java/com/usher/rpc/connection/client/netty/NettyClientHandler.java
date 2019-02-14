package com.usher.rpc.connection.client.netty;

import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connection.RpcFutureManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<RpcResponse> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {
        log.info("RPC RESPONSE: {}", rpcResponse.getResult());
        RpcFutureManager futureManager = RpcFutureManager.getInstance();
        futureManager.notifyFutureResponse(rpcResponse.getRequestId(), rpcResponse);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
