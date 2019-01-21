package com.usher.rpc.serializor;

import com.usher.rpc.common.SerializorType;
import com.usher.rpc.serializor.hessian.HessianSerializor;

public abstract class Serializor {
    public static Serializor DEFAULT_SERIALIZOR = new HessianSerializor();
    /**
     * 序列化
     * @param serializeObj
     * @return
     */
    public abstract byte[] serialize(Object serializeObj);

    /**
     * 反序列化
     * @param bytes
     * @param tClass
     * @param <T>
     * @return
     */
    public abstract <T> T deserialize(byte[] bytes, Class<T> tClass);
}
