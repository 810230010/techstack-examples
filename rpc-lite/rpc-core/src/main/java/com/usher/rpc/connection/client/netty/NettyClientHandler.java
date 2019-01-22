package com.usher.rpc.connection.client.netty;

import com.usher.rpc.codec.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
    private RpcResponse rpcResponse;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {
        log.info("RPC RESPONSE: {}", rpcResponse.getResult());
        channelHandlerContext.channel().attr(AttributeKey.valueOf("RPC-RESPONSE")).set(rpcResponse);
        channelHandlerContext.channel().close();
        channelHandlerContext.close();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
