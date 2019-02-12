package com.usher.rpc.common;

import org.springframework.util.StringUtils;

public enum RegistryType {
    REDIS, ZOOKEEPER, LOCAL;
    private RegistryType(){}

    /**
     * 查询某个注册中心类型是否在定义的注册类型中
     * @param type
     * @return
     */
    public static boolean matchRegistryType(String type){
        if(StringUtils.isEmpty(type))
            return false;
        RegistryType[] registryTypes = RegistryType.values();
        for(RegistryType registryType : registryTypes){
            if(registryType.name().compareToIgnoreCase(type) == 0){
                return true;
            }
        }
        return false;
    }

    public static String getRegistryTypeStr(RegistryType registryType, boolean ignoreCase){
        return ignoreCase ? registryType.name() : registryType.name().toLowerCase();
    }

    public boolean isEqualTo(String type){
        String name = this.name();
        if(name.equalsIgnoreCase(type))
            return true;
        return false;
    }
}
