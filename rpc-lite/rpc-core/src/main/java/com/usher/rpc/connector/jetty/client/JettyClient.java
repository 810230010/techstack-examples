package com.usher.rpc.connector.jetty.client;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.common.client.IClient;
import com.usher.rpc.util.HttpClientUtil;
import org.springframework.util.Assert;

public class JettyClient extends IClient {

    @Override
    public RpcResponse sendRequest(RpcRequest rpcRequest) {
        Assert.notNull(rpcRequest, "请求不得为空!");
        byte[] bytes = serializor.serialize(rpcRequest);
        String url = "http://" + serverAddress + ":" + port + "/";
        byte[] response = HttpClientUtil.postRequest(url, bytes);
        RpcResponse rpcResponse = (RpcResponse) serializor.deserialize(response, RpcResponse.class);
        return rpcResponse;
    }
}
