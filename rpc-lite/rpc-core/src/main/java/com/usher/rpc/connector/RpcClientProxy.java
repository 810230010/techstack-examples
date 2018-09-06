package com.usher.rpc.connector;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.common.client.IClient;
import com.usher.rpc.serialization.HessianSerializor;
import com.usher.rpc.common.serialization.Serializor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Proxy;

@Slf4j
public class RpcClientProxy<T> implements InitializingBean {
    private Class<T>[] rpcIface;
    private String serverAddress;
    /**
     * socket 通讯,地址->ip
     **/
    private int port;
    private Serializor serializor = new HessianSerializor();

    public Class<T>[] getRpcIface() {
        return rpcIface;
    }

    public void setRpcIface(Class<T>[] rpcIface) {
        this.rpcIface = rpcIface;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public int getPort() {
        return port;
    }

    private IClient client;

    public void setPort(int port) {
        this.port = port;
    }

    public RpcClientProxy(Class[] iface, String serverAddress, int port, Serializor serializor, IClient client) {
        this.rpcIface = iface;
        this.serverAddress = serverAddress;
        this.port = port;
        this.serializor = serializor;
        this.client = client;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        client.init(serverAddress, port, serializor);
    }

    public T getObject(Class ifaceClass) throws Exception {
        for (Class iface : rpcIface) {
            if (ifaceClass.getName().equals(iface.getName())) {
                return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{iface},
                        (proxy, method, args) -> {
                            RpcRequest request = new RpcRequest();
                            request.setClassName(method.getDeclaringClass().getName());
                            request.setMethodName(method.getName());
                            request.setParams(args);
                            request.setParameterTypes(method.getParameterTypes());
                            return client.sendRequest(request).getResult();
                        });
            }
        }
        return null;
    }

}
