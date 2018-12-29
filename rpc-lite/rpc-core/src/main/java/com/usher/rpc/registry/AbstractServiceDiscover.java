package com.usher.rpc.registry;

public abstract class AbstractServiceDiscover {
    protected String address;

    public AbstractServiceDiscover(String zkAddress){
        this.address = zkAddress;
    }
    /**
     * 发现服务
     * @param interfaceName
     * @return
     */
    public abstract String getService(String interfaceName);
}
