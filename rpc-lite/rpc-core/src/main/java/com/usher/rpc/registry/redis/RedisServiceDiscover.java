package com.usher.rpc.registry.redis;

import com.usher.rpc.registry.AbstractServiceDiscover;

public class RedisServiceDiscover extends AbstractServiceDiscover {
    public RedisServiceDiscover(String _registryAddress, int _registryPort) {
        super(_registryAddress, _registryPort);
    }

    @Override
    public String getService(String interfaceName) {
        return null;
    }
}
