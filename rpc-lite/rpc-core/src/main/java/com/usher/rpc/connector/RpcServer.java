package com.usher.rpc.connector;

import com.usher.rpc.annotation.RpcService;
import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.common.server.IServer;
import com.usher.rpc.serialization.HessianSerializor;
import com.usher.rpc.common.serialization.Serializor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class RpcServer implements InitializingBean,ApplicationContextAware,DisposableBean {
    private IServer server;
    private int port;
    private Serializor serializor = new HessianSerializor();

    public Serializor getSerializor() {
        return serializor;
    }

    public void setSerializor(Serializor serializor) {
        this.serializor = serializor;
    }

    static Map<String, Object> serviceMap = new HashMap<>();
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

    public IServer getServer() {
        return server;
    }

    public void setServer(IServer server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static RpcResponse invokeServiceImpl(RpcRequest request, Object service){
        if(service == null){
            service = serviceMap.get(request.getClassName());
        }
        RpcResponse response = new RpcResponse();
        Class clazz = service.getClass();
        String methodName = request.getMethodName();
        Object[] parameters = request.getParams();
        Class[] parameterTypes = request.getParameterTypes();
        try {
            Method method = clazz.getMethod(methodName, parameterTypes);
            try {
                Object result = method.invoke(service, parameters);
                response.setResult(result);
                return response;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return response;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("rpc server初始化完成.., port:{}", this.port);
        server.startServer(port, serializor);
        log.info("rpc server启动，正在监听..");
    }

    @Override
    public void destroy() throws Exception {
        server.destroy();
        log.info("内置服务关闭!");
    }
}
