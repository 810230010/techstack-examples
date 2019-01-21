package com.usher.rpc.registry.local;

import com.usher.rpc.registry.AbstractServiceDiscover;

public class LocalServiceDiscover extends AbstractServiceDiscover {
    public LocalServiceDiscover(String address, int port) {
        super(address, port);
    }

    @Override
    public String getService(String interfaceName) {
        return null;
    }
}
