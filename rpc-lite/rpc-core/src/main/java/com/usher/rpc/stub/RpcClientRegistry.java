package com.usher.rpc.stub;

import com.usher.rpc.connection.AbstractNetcomClient;
import com.usher.rpc.registry.AbstractServiceDiscover;
import com.usher.rpc.serializor.Serializor;
import lombok.Data;

@Data
public class RpcClientRegistry {
    private String serverAddress;
    private int port;
    private AbstractNetcomClient client;
    private Serializor serializor;
    private AbstractServiceDiscover serviceDiscover;
}
