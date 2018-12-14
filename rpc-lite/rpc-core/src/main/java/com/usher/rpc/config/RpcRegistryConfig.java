package com.usher.rpc.config;

import lombok.Data;

@Data
public class RpcRegistryConfig {
    private String registryAddress;
    private int registryPort;
}
