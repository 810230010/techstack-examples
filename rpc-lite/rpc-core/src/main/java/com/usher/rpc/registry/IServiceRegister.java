package com.usher.rpc.registry;

import java.util.Set;

public abstract class IServiceRegister {
    /**
     * 注册服务
     */
    public abstract void  registerService(String host, int port, Set<String> services);
}
