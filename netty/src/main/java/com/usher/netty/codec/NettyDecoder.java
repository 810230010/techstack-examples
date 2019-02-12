package com.usher.netty.codec;

import com.usher.serializor.HessianSerializor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class NettyDecoder extends ByteToMessageDecoder {
    private Class<?> targetClass;

    public NettyDecoder(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int totalDataLength = byteBuf.readableBytes();
        if(totalDataLength < 4){
            return;
        }
        byteBuf.markReaderIndex();
        int dataLenth = byteBuf.readInt();
        if(byteBuf.readableBytes() < dataLenth){
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] bytes = new byte[dataLenth];
        byteBuf.readBytes(bytes);
        Object obj = new HessianSerializor().deserialize(bytes, targetClass);
        list.add(obj);
    }
}
