package com.usher.rpc.registry;

public abstract class IServiceDiscover {
    /**
     * 发现服务
     * @param interfaceName
     * @return
     */
    public abstract String getService(String interfaceName);
}
