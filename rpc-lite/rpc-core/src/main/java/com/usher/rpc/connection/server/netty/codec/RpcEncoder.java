package com.usher.rpc.connection.server.netty.codec;

import com.usher.rpc.serializor.Serializor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcEncoder extends MessageToByteEncoder<Object> {
    private Class<?> clazz;
    private Serializor serializor;
    public RpcEncoder(Class clazz, Serializor serializor){
        this.clazz = clazz;
        this.serializor = serializor;
    }
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf out) throws Exception {
        if(clazz.isInstance(o)){
            byte[] data = serializor.serialize(o);
            int dataLen = data.length;
            out.writeInt(dataLen);
            out.writeBytes(data);
        }
    }
}
