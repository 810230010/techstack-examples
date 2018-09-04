package com.usher.rpc.connector;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.common.client.IClient;
import com.usher.rpc.serialization.HessianSerializor;
import com.usher.rpc.serialization.Serializor;
import com.usher.rpc.util.ByteUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.Socket;
@Slf4j
public class RpcClientProxy<T>{
    private Class<T> rpcIface;
    private String serverAddress; /** socket 通讯,地址->ip **/
    private int port;
    private Serializor serializor = new HessianSerializor();
    public Class<T> getRpcIface() {
        return rpcIface;
    }

    public void setRpcIface(Class<T> rpcIface) {
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

    public void setPort(int port) {
        this.port = port;
    }
    public RpcClientProxy(Class iface, String serverAddress, int port, Serializor serializor){
        this.rpcIface = iface;
        this.serverAddress = serverAddress;
        this.port = port;
        this.serializor = serializor;
    }

    public static class Builder<T>{
        private Class<T> rpcIface;
        private String serverAddress; /** socket 通讯,地址->ip **/
        private int port;
        private Serializor serializor = new HessianSerializor();
        public Builder serverAddress(String address){
            this.serverAddress = address;
            return this;
        }
        public Builder port(int port){
            this.port = port;
            return this;
        }
        public Builder iface(Class clazz){
            this.rpcIface = clazz;
            return this;
        }
        public Builder serializor(Serializor serializor){
            this.serializor = serializor;
            return this;
        }
        public RpcClientProxy build(){
            return new RpcClientProxy(this.rpcIface, this.serverAddress, this.port, serializor);
        }
    }

    private IClient client;
    public T getObject() throws Exception {
        return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{rpcIface},
                (proxy, method, args) -> {
                    RpcRequest request = new RpcRequest();
                    request.setClassName(method.getDeclaringClass().getName());
                    request.setMethodName(method.getName());
                    request.setParams(args);
                    request.setParameterTypes(method.getParameterTypes());
                    return client.sendRequest(request);
        });
    }

}
