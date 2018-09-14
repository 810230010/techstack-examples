package com.usher.netty.client;

import com.usher.serializor.HessianSerializor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class NettyEncoder extends MessageToByteEncoder {
    private HessianSerializor serializor;
    public NettyEncoder(HessianSerializor serializor){
        this.serializor = serializor;
    }
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object in, ByteBuf out) throws Exception {
        byte[] bytes = serializor.serialize(in);
        int dataLen = bytes.length;
        out.writeInt(dataLen);
        out.writeBytes(bytes);
    }
}
