package com.usher.rpc.config;

import lombok.Data;

@Data
public class RpcRegistryConfig {
    private String registryId;
    private String registryAddress;
    private int registryPort;
    /**
     * REDIS、ZOOKEEPER、STANDALONE
     */
    private String registryType;
}
