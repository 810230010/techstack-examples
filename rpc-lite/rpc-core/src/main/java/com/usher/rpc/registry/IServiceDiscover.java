package com.usher.rpc.registry;

public abstract class IServiceDiscover {
    protected String address;
    public IServiceDiscover (String zkAddress){
        this.address = zkAddress;
    }
    /**
     * 发现服务
     * @param interfaceName
     * @return
     */
    public abstract String getService(String interfaceName);
}
