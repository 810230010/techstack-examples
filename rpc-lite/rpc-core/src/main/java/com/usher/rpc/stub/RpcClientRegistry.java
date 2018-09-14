package com.usher.rpc.stub;

import com.usher.rpc.connection.IClient;
import com.usher.rpc.serializor.Serializor;
import lombok.Data;

@Data
public class RpcClientRegistry {
    private String serverAddress;
    private int port;
    private IClient client;
    private Serializor serializor;
}
