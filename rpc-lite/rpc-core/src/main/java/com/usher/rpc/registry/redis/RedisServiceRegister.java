package com.usher.rpc.registry.redis;

import com.usher.rpc.registry.IServiceRegister;

import java.util.Set;

public class RedisServiceRegister extends IServiceRegister {

    public RedisServiceRegister(String address, int port) {
        super(address, port);
    }

    @Override
    public void registerService(Set<String> services) {

    }

    @Override
    public void stop() {

    }
}
