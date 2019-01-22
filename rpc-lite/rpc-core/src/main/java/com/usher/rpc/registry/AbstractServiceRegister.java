package com.usher.rpc.registry;

import lombok.Data;

import java.util.Map;
@Data
public abstract class AbstractServiceRegister {
    protected String address;
    protected int port;
    public AbstractServiceRegister(String address, int port){
        this.address = address;
        this.port = port;
    }
    /**
     * 注册服务
     */
    public abstract void  registerService(Map<String, String> services);

    public abstract void stop();
}
