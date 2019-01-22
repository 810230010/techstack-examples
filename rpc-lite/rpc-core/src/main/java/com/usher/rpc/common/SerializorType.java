package com.usher.rpc.common;

import org.springframework.util.StringUtils;

public enum  SerializorType {
    JSON,HESSIAN,KRYO;
    SerializorType(){
    }
    public static boolean matchSerializorType(String type){
        if(StringUtils.isEmpty(type))
            return false;
        SerializorType[] serializorTypes = SerializorType.values();
        for(SerializorType serializorType : serializorTypes){
            if(serializorType.name().compareToIgnoreCase(type) == 0){
                return true;
            }
        }
        return false;
    }
}
