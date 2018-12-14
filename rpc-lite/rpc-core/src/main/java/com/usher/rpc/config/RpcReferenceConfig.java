package com.usher.rpc.config;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
@Data
public class RpcReferenceConfig implements ApplicationContextAware, InitializingBean {
    private String id;
    private String ifaceName;
    private long timeout;
    private RpcProtocolConfig protocolConfig;
    private RpcRegistryConfig registryConfig;
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
