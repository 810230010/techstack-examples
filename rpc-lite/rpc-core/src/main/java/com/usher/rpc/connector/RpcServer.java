package com.usher.rpc.connector;

import com.usher.rpc.annotation.RpcService;
import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.common.server.IServer;
import com.usher.rpc.serialization.HessianSerializor;
import com.usher.rpc.serialization.Serializor;
import com.usher.rpc.util.AppContextHolder;
import com.usher.rpc.util.ByteUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class RpcServer implements InitializingBean,ApplicationContextAware {
    private IServer server;
    private int port;
    private Serializor serializor = new HessianSerializor();

    public Serializor getSerializor() {
        return serializor;
    }

    public void setSerializor(Serializor serializor) {
        this.serializor = serializor;
    }

    Map<String, Object> serviceMap = new HashMap<>();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(RpcService.class);
        for(Map.Entry<String, Object> entry : map.entrySet()){
            Object bean = entry.getValue();
            String ifaceName = bean.getClass().getAnnotation(RpcService.class).value().getName();
            serviceMap.put(ifaceName, bean);
        }
        System.out.println(map);
    }
    public RpcServer() {
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static void invokeServiceImpl(Object service, RpcRequest request){

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("rpc server初始化完成.., {}", this);
        server.startServer(port, serializor);
        log.info("rpc server启动，正在监听..");
    }


}
