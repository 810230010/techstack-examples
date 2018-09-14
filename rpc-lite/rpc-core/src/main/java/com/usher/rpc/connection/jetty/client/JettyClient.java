package com.usher.rpc.connection.jetty.client;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connection.IClient;
import com.usher.rpc.util.HttpClientUtil;

import java.text.MessageFormat;

public class JettyClient extends IClient {
    @Override
    public RpcResponse sendRequest(RpcRequest rpcRequest) {
        String serverAddress = rpcRequest.getServerAddress();
        int port = rpcRequest.getPort();

        String reqUrl = String.format("http://%s:%d/", serverAddress, port);
        byte[] bytes = serializor.serialize(rpcRequest);
        byte[] response = HttpClientUtil.postRequest(reqUrl, bytes);
        return serializor.deserialize(response, RpcResponse.class);
    }
}
