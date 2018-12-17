package com.usher.rpc.common;

import org.springframework.util.StringUtils;

public enum LoadbalanceType {
    RANDOM,CONSISTENT_HASH,ROUND_ROBIN;
    private LoadbalanceType(){}
    public static boolean matchLoadbalanceType(String type){
        if(StringUtils.isEmpty(type))
            return false;
        LoadbalanceType[] loadbalanceTypes = LoadbalanceType.values();
        for(LoadbalanceType registryType : loadbalanceTypes){
            if(registryType.name().compareToIgnoreCase(type) == 0){
                return true;
            }
        }
        return false;
    }
}
