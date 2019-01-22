package com.usher.rpc.common;

import com.usher.rpc.serializor.Serializor;
import com.usher.rpc.serializor.hessian.HessianSerializor;

public enum SerializorEnum {
    HESSIAN(HessianSerializor.class), JSON(null), KRYO(null);
    Class<? extends Serializor> serializor;
    SerializorEnum(Class<? extends Serializor> serializor){
        this.serializor = serializor;
    }

    public static SerializorEnum macth(String serialzorType) {
            for(SerializorEnum serializorEnum : SerializorEnum.values()){
                if(serializorEnum.name().equals(serialzorType)){
                    return serializorEnum;
                }
            }
            return null;
    }

    public Class<? extends Serializor> getSerializor() {
        return serializor;
    }

    public void setSerializor(Class<? extends Serializor> serializor) {
        this.serializor = serializor;
    }
}
