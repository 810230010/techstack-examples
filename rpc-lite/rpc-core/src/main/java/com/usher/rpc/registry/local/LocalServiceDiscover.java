package com.usher.rpc.registry.local;

import com.usher.rpc.registry.AbstractServiceDiscover;

public class LocalServiceDiscover extends AbstractServiceDiscover {
    public LocalServiceDiscover(String zkAddress) {
        super(zkAddress);
    }

    @Override
    public String getService(String interfaceName) {
        return null;
    }
}
