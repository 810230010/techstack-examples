package com.usher.rpc.stub;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connection.AbstractClient;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;
@Data
public class RpcClientProxy<T> implements InitializingBean {
    private RpcClientRegistry rpcClientRegistry;
    private String className;
    private Class<T> iface;
    private AbstractClient client;


    @Override
    public void afterPropertiesSet() throws Exception {
        client = rpcClientRegistry.getClient();
        client.init(rpcClientRegistry);
    }

    public T getProxy(){
        return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{iface}, (Object proxy, Method m, Object[] args)->{
            RpcRequest request = new RpcRequest();
            request.setRequestId(UUID.randomUUID().toString());
            request.setIfaceName(className);
            request.setParams(args);
            request.setMethodName(m.getName());
            request.setParamTypes(m.getParameterTypes());
            request.setServerAddress(rpcClientRegistry.getServerAddress());
            request.setPort(rpcClientRegistry.getPort());

            RpcResponse response = client.sendRequest(request);
            return response.getResult();
        });
    }
}
