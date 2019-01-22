package com.usher.rpc.stub;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connection.AbstractNetcomServer;
import com.usher.rpc.registry.AbstractServiceRegister;
import com.usher.rpc.serializor.Serializor;
import lombok.Data;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class RpcServiceInvoker {


    public static RpcResponse invokeService(RpcRequest request){
        String methodName = request.getMethodName();
        String requestId = request.getRequestId();
        String className = request.getIfaceName();
        Class[] paramTypes = request.getParamTypes();
        Object[] params = request.getParams();

        Object serviceBean = serviceMap.get(className);
        Class serviceClass = serviceBean.getClass();

        FastClass fastClass = FastClass.create(serviceClass);
        FastMethod fastMethod = fastClass.getMethod(methodName, paramTypes);
        Object result = null;
        try {
            result = fastMethod.invoke(serviceBean, params);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        RpcResponse response = new RpcResponse();
        response.setRequestId(requestId);
        response.setResult(result);
        return response;
    }

    public static Map<String, Object> serviceMap = new ConcurrentHashMap<>();
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
////        Map<String, Object> map = applicationContext.getBeansWithAnnotation(RpcService.class);
////        for(Map.Entry<String, Object> entry : map.entrySet()){
////            String ifaceName = entry.getValue().getClass().getAnnotation(RpcService.class).value().getName();
////            Object bean = entry.getValue();
////            serviceMap.put(ifaceName, bean);
////        }
//    }
//
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
////        server.init(port, serializor).startServer();
////        if(serviceRegister != null){
////            Set<String> serviceSet = new LinkedHashSet<>();
////            for(Map.Entry<String, Object> entry : serviceMap.entrySet()){
////                serviceSet.add(entry.getKey());
////            }
////            serviceRegister.registerService(serviceSet);
////        }
//    }
}
