package com.usher.rpc.serializor;

public abstract class Serializor {
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
