package com.usher.rpc.registry.redis;

import com.usher.rpc.registry.AbstractServiceRegister;

import java.util.Map;
import java.util.Set;

public class RedisServiceRegister extends AbstractServiceRegister {

    public RedisServiceRegister(String address, int port) {
        super(address, port);
    }

    @Override
    public void registerService(Map<String, String> services) {

    }

    @Override
    public void stop() {

    }
}
