package com.usher.rpc.connection.server.netty.codec;

import com.usher.rpc.serializor.Serializor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class RpcDecoder extends ByteToMessageDecoder {
    private Class<?> clazz;
    private Serializor serializor;
    public RpcDecoder(Class clazz, Serializor serializor){
        this.clazz = clazz;
        this.serializor = serializor;
    }
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < 4){
            return;
        }
        //netty的ByteBuf维护了两个索引，一个是读索引，一个是写索引
        //标记读索引的位置，方便到时候重置
        in.markReaderIndex();
        //数据长度
        int dataLen = in.readInt();
        if(in.readableBytes() < dataLen){
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLen];
        in.readBytes(data);

        Object obj = serializor.deserialize(data, clazz);
        out.add(obj);
    }
}
