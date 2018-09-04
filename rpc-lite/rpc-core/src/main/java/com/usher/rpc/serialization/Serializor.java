package com.usher.rpc.serialization;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public abstract class Serializor {
    public abstract <T extends Serializable>byte[] serialize(T src);

    public abstract  <T extends Serializable>Object deserialize(byte[] src, Class<T> clazz);
}
