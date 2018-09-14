package com.usher.netty.server;

import com.usher.serializor.HessianSerializor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class NettyDecoder extends ByteToMessageDecoder {
    private HessianSerializor serializor;
    public NettyDecoder(HessianSerializor serializor){
        this.serializor = serializor;
    }
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        if(in.readableBytes() < 4){
            return;
        }
    }
}
