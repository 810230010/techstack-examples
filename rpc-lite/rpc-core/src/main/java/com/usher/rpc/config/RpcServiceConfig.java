package com.usher.rpc.config;

import com.usher.rpc.common.RegistryType;
import com.usher.rpc.common.ServiceURL;
import com.usher.rpc.registry.AbstractServiceRegister;
import com.usher.rpc.registry.local.LocalServiceRegister;
import com.usher.rpc.registry.redis.RedisServiceRegister;
import com.usher.rpc.registry.zookeeper.ZKServiceRegister;
import com.usher.rpc.util.NetUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.*;

@Data
@Slf4j
public class RpcServiceConfig implements ApplicationContextAware, InitializingBean, ApplicationListener<ContextRefreshedEvent> {
    private String id;
    //接口   com.xxx.xxx
    private String ifaceName;
    //接口实现类名  com.xxx.xxxImpl
    private String ref;

    //超时时间
    private long timeout;
    //负载均衡
    private String loadbalance;

    private String registryType;
    //注册中心地址
    private String registryAddress;
    //注册中心地址端口
    private int registryPort;

    //序列化方式
    private String serializor;
    //通讯组件  netty、mina、jetty
    private String netcom;
    //服务接口
    private int servicePort;


    @Override
    public void afterPropertiesSet(){
        log.info("service config属性填充完成，{},准备注册服务...,", this);
        export();
    }
    //localhost:8080/com.usher.iface.UserService?serializor=HESSIAN
    private synchronized void export() {
        String serviceHost= NetUtils.getLocalHostAddress();
        int availableServicePort = NetUtils.getAvailablePort(servicePort);
        ServiceURL serviceURL = new ServiceURL(serviceHost, availableServicePort, ifaceName, getParams());
        String urlString = serviceURL.toString();
        log.info("服务地址: {}", urlString);
        AbstractServiceRegister serviceRegister = null;
        if(RegistryType.LOCAL.isEqualTo(registryType)){
            serviceRegister = new LocalServiceRegister(registryAddress, registryPort);
        }else if(RegistryType.REDIS.isEqualTo(registryType)){
            serviceRegister = new ZKServiceRegister(registryAddress, registryPort);
        }else {
            serviceRegister = new RedisServiceRegister(registryAddress, registryPort);
        }

        Map<String, String> service = new TreeMap<>();
        service.put(ifaceName, urlString);
        serviceRegister.registerService(service);
    }

    private Map<String,String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("serializor", serializor);
        params.put("netcom", netcom);
        params.put("ref", ref);
        return params;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }

    @Override
    public String toString() {
        return "RpcServiceConfig{" +
                "id='" + id + '\'' +
                ", ifaceName='" + ifaceName + '\'' +
                ", ref='" + ref + '\'' +
                ", timeout=" + timeout +
                ", loadbalance='" + loadbalance + '\'' +
                ", registryAddress='" + registryAddress + '\'' +
                ", registryPort=" + registryPort +
                ", serializor='" + serializor + '\'' +
                ", netcom='" + netcom + '\'' +
                '}';
    }
}
