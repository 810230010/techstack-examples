package com.usher.rpc.common;

import org.springframework.util.StringUtils;

public enum  NetcomType {
    JETTY, NETTY, MINA;
    NetcomType(){}
    public static boolean matchNetcomType(String type){
        if(StringUtils.isEmpty(type))
            return false;
        NetcomType[] netcomTypes = NetcomType.values();
        for(NetcomType netcomType : netcomTypes){
            if(netcomType.name().compareToIgnoreCase(type) == 0){
                return true;
            }
        }
        return false;
    }

    public boolean isEqualTo(String type){
        String name = this.name();
        if(type.equalsIgnoreCase(type))
            return true;
        return false;
    }
}
