package com.usher.rpc.registry;

import com.usher.rpc.cluster.router.ServiceRouteStrategy;

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
    /**
     * 发现服务
     * @param interfaceName
     * @param serviceRouteStrategy  路由策略:轮询、一致性hash、
     * @return
     */
    public abstract String getService(String interfaceName, ServiceRouteStrategy serviceRouteStrategy);
}
