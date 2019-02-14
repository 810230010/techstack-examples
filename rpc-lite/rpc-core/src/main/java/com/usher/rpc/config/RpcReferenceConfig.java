package com.usher.rpc.config;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.common.RegistryType;
import com.usher.rpc.common.ServiceURL;
import com.usher.rpc.connection.AbstractNetcomClient;
import com.usher.rpc.connection.RpcFutureManager;
import com.usher.rpc.connection.RpcFutureReponse;
import com.usher.rpc.connection.client.jetty.JettyClient;
import com.usher.rpc.factory.NetcomClientFactory;
import com.usher.rpc.registry.AbstractServiceDiscover;
import com.usher.rpc.registry.local.LocalServiceDiscover;
import com.usher.rpc.registry.redis.RedisServiceDiscover;
import com.usher.rpc.serializor.Serializor;
import com.usher.rpc.util.NetUtils;
import com.usher.rpc.util.StringUtils;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Data
public class RpcReferenceConfig<T> implements ApplicationContextAware, InitializingBean, FactoryBean {
    private String                          id;
    private String                          ifaceName;
    //超时
    private long                            timeout;

    private T                               ref;

    private String                          registryType;
    //注册中心地址
    private String                          registryAddress;
    //注册中心地址端口
    private int                             registryPort;

    private String                          serializor;
    private String                          netcom;
    private int                             servicePort;

    private AbstractNetcomClient            client;
    private AbstractServiceDiscover         serviceDiscover;
    private transient ApplicationContext    applicationContext;

    //服务地址缓存
    private static Map<String, String> serviceCache = new ConcurrentHashMap<>();

    @Override
    public void afterPropertiesSet(){
        getObject();
        initServiceDiscover(registryType, registryAddress, registryPort);
        initClient(registryType, registryAddress, registryPort);
    }



    private void initServiceDiscover(String registryType, String registryAddress, int registryPort) {
        if(RegistryType.LOCAL.isEqualTo(registryType)){
            serviceDiscover = new LocalServiceDiscover(registryAddress, registryPort);
        }else if(RegistryType.REDIS.isEqualTo(registryType)){
            serviceDiscover = new RedisServiceDiscover(registryAddress, registryPort);
        }
    }
    private void initClient(String registryType, String registryAddress, int registryPort){
        if(RegistryType.LOCAL.isEqualTo(registryType)){
            String localAddress = NetUtils.getLocalHostAddress();
            if(null != netcom && 0 != servicePort && null != serializor){
                client = NetcomClientFactory.newClient(netcom, localAddress, servicePort, serializor);
            }else{
                client = new JettyClient(localAddress, registryPort, Serializor.DEFAULT_SERIALIZOR);
            }

        }else{
            String serviceUrl = serviceDiscover.getService(ifaceName);
            ServiceURL url = ServiceURL.toServiceURL(serviceUrl);
            String serializorType = url.getParamValueFor(ServiceURL.SERIALIZOR_KEY);
            String netcomType = url.getParamValueFor(ServiceURL.NETCOM_KEY);
            int port = url.getPort();
            String serverAddress = url.getHost();
            client = NetcomClientFactory.newClient(netcomType, serverAddress, port, serializorType);
        }
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object getObject(){
        Class iface = null;
        RpcRequest request = new RpcRequest();
        request.setRequestId(StringUtils.generateUUID());
        request.setIfaceName(ifaceName);
        request.setPort(registryPort);
        request.setServerAddress(registryAddress);
        if(ref == null){
            try {
                iface = Thread.currentThread().getContextClassLoader().loadClass(ifaceName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }else{
            iface = ref.getClass();
        }
        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{iface},
                (target, method, params)->{
                    String methodName = method.getName();
                    request.setMethodName(methodName);
                    request.setParams(params);
                    Class[] paramTypes = method.getParameterTypes();
                    request.setParamTypes(paramTypes);
                    request.setRegistryType(registryType);
                    if(isObjectMethod(methodName)){
                        throw new IllegalStateException("unsupported method...");
                    }
                    RpcFutureManager rpcFutureManager = RpcFutureManager.getInstance();
                    RpcFutureReponse futureReponse = new RpcFutureReponse().addIntoFutureManager(rpcFutureManager)
                            .addRpcRequest(request);
                    client.sendRequest(request);
                    RpcResponse response = null;
                    try {
                        response = futureReponse.get(timeout, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        throw new TimeoutException("返回结果超时");
                    }
                    return response.getResult();
                });
        return proxy;
    }




    @Override
    public Class<?> getObjectType() {
        return null;
    }


    private boolean isObjectMethod(String methodName){
        Method[] methods = Object.class.getMethods();
        List<String> names = new ArrayList<>();
        for(Method method : methods){
            String name = method.getName();
            names.add(name);
        }
        if(names.contains(methodName)){
            return true;
        }
        return false;
    }
}
