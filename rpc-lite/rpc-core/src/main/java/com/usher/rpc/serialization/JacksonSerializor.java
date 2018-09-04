package com.usher.rpc.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.Serializable;

public class JacksonSerializor extends Serializor {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public <T extends Serializable> byte[] serialize(T src) {
        Assert.notNull(src, "待序列化的对象不得为空!");
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(src);
            return bytes;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public <T extends Serializable> Object deserialize(byte[] src, Class<T> clazz) {
        try {
            return objectMapper.readValue(src, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
