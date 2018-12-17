package com.usher.rpc.config;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
@Data
public class RpcServiceConfig implements ApplicationContextAware, InitializingBean {
    private String id;
    //接口   com.xxx.xxx
    private String ifaceName;
    //接口实现类名  com.xxx.xxxImpl
    private String ref;


    //超时时间
    private long timeout;
    //负载均衡
    private String loadbalance;

    //注册中心地址
    private String registryAddress;
    //注册中心地址端口
    private int registryPort;

    //序列化方式
    private String serializor;
    //通讯组件  netty、mina、jetty
    private String netcom;

    private RpcRegistryConfig registry;
    private RpcProtocolConfig protocol;


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
