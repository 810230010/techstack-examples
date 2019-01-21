package com.usher.rpc.registry;

public abstract class AbstractServiceDiscover {
    protected String registryAddress;
    protected int registryPort;
    public AbstractServiceDiscover(String _address, int _port){
        this.registryAddress = _address;
        this.registryPort = _port;
    }
    /**
     * 发现服务
     * @param interfaceName
     * @return
     */
    public abstract String getService(String interfaceName);
}
