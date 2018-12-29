package com.usher.rpc.connection.jetty.client;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connection.AbstractClient;
import com.usher.rpc.registry.AbstractServiceDiscover;
import com.usher.rpc.util.HttpClientUtils;

public class JettyClient extends AbstractClient {
    @Override
    public RpcResponse sendRequest(RpcRequest rpcRequest) {
        String serverAddress = rpcRequest.getServerAddress();
        int port = rpcRequest.getPort();
        String reqUrl = null;
        String interfaceName = rpcRequest.getIfaceName();
        AbstractServiceDiscover serviceDiscover = rpcRequest.getServiceDiscover();
        if(null != serviceDiscover){
            reqUrl = String.format("http://%s/", serviceDiscover.getService(interfaceName));
        }else{
            reqUrl = String.format("http://%s:%d/", serverAddress, port);
        }

        byte[] bytes = serializor.serialize(rpcRequest);
        byte[] response = HttpClientUtils.postRequest(reqUrl, bytes);
        return serializor.deserialize(response, RpcResponse.class);
    }
}
