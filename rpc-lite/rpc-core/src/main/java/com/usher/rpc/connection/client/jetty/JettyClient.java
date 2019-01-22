package com.usher.rpc.connection.client.jetty;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connection.AbstractNetcomClient;
import com.usher.rpc.serializor.Serializor;
import com.usher.rpc.util.HttpClientUtils;

public class JettyClient extends AbstractNetcomClient {
    public JettyClient(String _serverAddress, int _serverPort, Serializor _serializor) {
        super(_serverAddress, _serverPort, _serializor);
    }

    @Override
    public RpcResponse sendRequest(RpcRequest rpcRequest) {

        String serverAddress = rpcRequest.getServerAddress();
        int port = rpcRequest.getPort();
        String reqUrl = null;
        String interfaceName = rpcRequest.getIfaceName();
        reqUrl = String.format("http://%s:%d/", serverAddress, port);

        byte[] bytes = serializor.serialize(rpcRequest);
        byte[] response = HttpClientUtils.postRequest(reqUrl, bytes);
        return serializor.deserialize(response, RpcResponse.class);
    }
}
