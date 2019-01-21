package com.usher.rpc.config;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.common.RegistryType;
import com.usher.rpc.common.SerializorType;
import com.usher.rpc.common.ServiceURL;
import com.usher.rpc.connection.AbstractNetcomClient;
import com.usher.rpc.connection.jetty.client.JettyClient;
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

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
            client = new JettyClient(localAddress, NetUtils.getAvailablePort(0),
                    Serializor.DEFAULT_SERIALIZOR);
        }else if(RegistryType.REDIS.isEqualTo(registryType)){
            String serviceUrl = serviceDiscover.getService(ifaceName);

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

                    RpcResponse response = client.sendRequest(request);
                    return response.getResult();
                });
        return proxy;
    }




    @Override
    public Class<?> getObjectType() {
        return null;
    }

}
