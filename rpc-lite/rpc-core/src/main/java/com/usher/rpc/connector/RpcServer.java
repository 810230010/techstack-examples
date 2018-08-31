package com.usher.rpc.connector;

import com.usher.rpc.annotation.RpcService;
import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.util.AppContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class RpcServer implements InitializingBean,ApplicationContextAware {

    private int port;

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

    public void startServer(){
        try(ServerSocket ss = new ServerSocket(port)) {
            while(true){
                try(Socket s = ss.accept()){
                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                    Object result = null;
                        RpcRequest request = (RpcRequest)ois.readObject();
                        String methodName = request.getMethodName();
                        String clazzName = request.getClassName();
                        Class[] paramterTypes = request.getParameterTypes();
                        Object[] params = request.getParams();

                        Object bean = serviceMap.get(clazzName);
                        Class clazz = bean.getClass();
                        Method m = clazz.getMethod(methodName, paramterTypes);
                        Object res = m.invoke(bean, params);
                        try (ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream())){
                            RpcResponse response = new RpcResponse();
                            response.setResult(res);
                            oos.writeObject(response);
                            oos.flush();
                        }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("rpc server初始化完成.., {}", this);
        startServer();
        log.info("rpc server启动，正在监听..");
    }


}
