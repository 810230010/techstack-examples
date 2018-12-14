package com.usher.rpc.config;

import com.usher.rpc.serializor.Serializor;
import com.usher.rpc.stub.RpcClientRegistry;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Data
public class RpcProtocolConfig implements InitializingBean, ApplicationContextAware {
    private String serializor;
    private String netcom;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
