package com.usher.rpc.registry;

import lombok.Data;

import java.util.Set;
@Data
public abstract class IServiceRegister {
    protected String address;
    protected int port;
    public IServiceRegister(String address, int port){
        this.address = address;
        this.port = port;
    }
    /**
     * 注册服务
     */
    public abstract void  registerService(Set<String> services);

    public abstract void stop();
}
