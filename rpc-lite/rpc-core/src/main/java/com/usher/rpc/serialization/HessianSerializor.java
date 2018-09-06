package com.usher.rpc.serialization;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.usher.rpc.common.serialization.Serializor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class HessianSerializor extends Serializor {
    @Override
    public <T extends Serializable> byte[] serialize(T src) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(bytes);
        try {
            output.writeObject(src);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output.close(); // flush to avoid EOF error
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes.toByteArray();
    }

    @Override
    public <T extends Serializable> T deserialize(byte[] src, Class<T> clazz) {
        ByteArrayInputStream bis = new ByteArrayInputStream(src);
        Hessian2Input input = new Hessian2Input(bis);
        try {
            T result = (T) input.readObject();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
