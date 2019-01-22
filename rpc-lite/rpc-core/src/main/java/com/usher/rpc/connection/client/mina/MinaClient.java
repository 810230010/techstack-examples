package com.usher.rpc.connection.client.mina;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connection.AbstractNetcomClient;
import com.usher.rpc.serializor.Serializor;

public class MinaClient extends AbstractNetcomClient {
    public MinaClient(String serverAddress, int serverPort, Serializor serializor) {
        super(serverAddress, serverPort, serializor);
    }

    @Override
    public RpcResponse sendRequest(RpcRequest rpcRequest) {
        return null;
    }
}
