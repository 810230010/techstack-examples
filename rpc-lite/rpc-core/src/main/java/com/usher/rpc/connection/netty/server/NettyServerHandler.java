package com.usher.rpc.connection.netty.server;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.stub.RpcServerInvoker;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<RpcRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcRequest request) throws Exception {
        log.info("收到netty客户端发送的请求!");
        RpcResponse response = RpcServerInvoker.invokeService(request);
        ChannelFuture future= channelHandlerContext.writeAndFlush(response);
        future.addListener((ChannelFuture f)->{
           if(f.isSuccess()){
               log.info("发送rp response成功!");
           }else{
               log.info("发送rp response失败!");
           }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("客户端连接上!");
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
