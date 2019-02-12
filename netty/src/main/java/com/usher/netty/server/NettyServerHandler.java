package com.usher.netty.server;

import com.usher.netty.entity.Result;
import com.usher.netty.entity.User;
import com.usher.netty.util.LoginUtil;
import com.usher.serializor.HessianSerializor;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<User> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx, User user) throws Exception {
        log.info("服务器收到用户登录请求: {}", user);

        Result result = new Result();
        if(user.getUsername().equals("jjp") && user.getPassword().equals("123")){
            result.setResult(true);
        }else{
            result.setResult(false);
        }


        ctx.channel().writeAndFlush(result);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("异常...");
    }
}
