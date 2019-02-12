package com.usher.netty.client;

import com.usher.netty.entity.Result;
import com.usher.netty.entity.User;
import com.usher.netty.util.LoginUtil;
import com.usher.serializor.HessianSerializor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<Result> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接服务器成功...  现在准备登录..");

    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Result result) throws Exception {
        if(result.isResult()){
            ctx.channel().attr(AttributeKey.valueOf("result")).set(true);
            log.info("登录成功..., channel:{}", ctx.channel());
        }else{
            ctx.channel().attr(AttributeKey.valueOf("result")).set(false);
            log.info("登录失败...");
        }
    }
}
