package com.usher.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buff = ctx.alloc().buffer(100);
        buff.writeBytes("hello client, you've successfully connected!".getBytes());
//        ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
        final ChannelFuture future = ctx.writeAndFlush(buff);
        future.addListener((ChannelFuture channelFuture)-> {
            if(channelFuture.isSuccess()){
                log.info("发送给客户端完成!");
                ctx.close();
            }else{
                log.info("发送出错!");
                channelFuture.cause().printStackTrace();
            }

        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("收到客户端发来的消息! {}", msg);
        ReferenceCountUtil.release(msg);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
