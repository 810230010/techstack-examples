package com.usher.rpc.registry.redis;

import com.usher.rpc.registry.AbstractServiceDiscover;

public class RedisServiceDiscover extends AbstractServiceDiscover {
    public RedisServiceDiscover(String zkAddress) {
        super(zkAddress);
    }

    @Override
    public String getService(String interfaceName) {
        return null;
    }
}