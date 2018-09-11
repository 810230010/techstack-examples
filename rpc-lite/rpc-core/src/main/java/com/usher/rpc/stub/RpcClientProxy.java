package com.usher.rpc.stub;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connection.IClient;
import com.usher.rpc.serializor.Serializor;
import com.usher.rpc.util.HttpClientUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.MessageFormat;
import java.util.UUID;

public class RpcClientProxy<T> implements InitializingBean {
    private String serverAddress;
    private int port;
    private String className;
    private Serializor serializor;
    private Class<T> iface;

    private IClient client;
    @Override
    public void afterPropertiesSet() throws Exception {
        client.init(serverAddress, port, serializor);
    }

    public T getProxy(){
        return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{iface}, (Object proxy, Method m, Object[] args)->{
            RpcRequest request = new RpcRequest();
            request.setRequestId(UUID.randomUUID().toString());
            request.setIfaceName(className);
            request.setParams(args);
            request.setMethodName(m.getName());
            request.setParamTypes(m.getParameterTypes());
            request.setServerAddress(serverAddress);
            request.setPort(port);

            RpcResponse response = client.sendRequest(request);
            return response.getResult();
        });
    }
}
