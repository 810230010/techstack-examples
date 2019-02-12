package com.usher.rpc.connection.client.netty;

import com.usher.rpc.codec.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
    private RpcResponse result;
    private CountDownLatch countDownLatch;


    public RpcResponse getResult() {
        return result;
    }

    public void setResult(RpcResponse result) {
        this.result = result;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {
        log.info("RPC RESPONSE: {}", rpcResponse.getResult());
        this.result = rpcResponse;
        channelHandlerContext.channel().writeAndFlush(rpcResponse).sync();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
