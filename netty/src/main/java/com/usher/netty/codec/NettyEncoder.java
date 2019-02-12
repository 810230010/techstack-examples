package com.usher.netty.codec;

import com.usher.netty.entity.User;
import com.usher.serializor.HessianSerializor;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;


public class NettyEncoder extends MessageToByteEncoder<Object> {
    private Class<?> targetClass;
    public NettyEncoder(Class<?> toBeSerializedClass){
        this.targetClass = toBeSerializedClass;
    }
    /**
     *
     * @param channelHandlerContext
     * @param
     * @param byteBuf  自动做回收
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object obj, ByteBuf byteBuf) throws Exception {
        if(targetClass.isInstance(obj)){
            byte[] bytes = new HessianSerializor().serialize(obj);
            int dataLength = bytes.length;
            byteBuf.writeInt(dataLength);
            byteBuf.writeBytes(bytes);
        }
    }
}
