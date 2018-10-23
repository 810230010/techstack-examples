package com.usher.rpc.registry.redis;

import com.usher.rpc.registry.IServiceDiscover;

public class RedisServiceDiscover extends IServiceDiscover {
    public RedisServiceDiscover(String zkAddress) {
        super(zkAddress);
    }

    @Override
    public String getService(String interfaceName) {
        return null;
    }
}
