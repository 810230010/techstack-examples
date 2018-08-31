package com.usher.rpc.connector;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.Socket;
@Slf4j
public class RpcClientProxy<T>{
    private Class<T> rpcIface;
    private String serverAddress; /** socket 通讯,地址->ip **/
    private int port;

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
    public RpcClientProxy(Class iface, String serverAddress, int port){
        this.rpcIface = iface;
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public static class Builder<T>{
        private Class<T> rpcIface;
        private String serverAddress; /** socket 通讯,地址->ip **/
        private int port;
        Builder serverAddress(String address){
            this.serverAddress = address;
            return this;
        }
        Builder port(int port){
            this.port = port;
            return this;
        }
        Builder iface(Class clazz){
            this.rpcIface = clazz;
            return this;
        }
        public RpcClientProxy build(){
            return new RpcClientProxy(this.rpcIface, this.serverAddress, this.port);
        }
    }
    public T getObject() throws Exception {
        return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{rpcIface},
                (proxy, method, args) -> {
                    RpcRequest request = new RpcRequest();
                    request.setClassName(method.getDeclaringClass().getName());
                    request.setMethodName(method.getName());
                    request.setParams(args);
                    request.setParameterTypes(method.getParameterTypes());
                    try(Socket socket = new Socket(serverAddress, port)){
                        //发送序列化后的请求
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(request);
                        oos.flush();
                        //接受响应
                        try(ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
                            Object result = null;
                                RpcResponse response = (RpcResponse) ois.readObject();
                                return response.getResult();
                        }
                    }
        });
    }


}
