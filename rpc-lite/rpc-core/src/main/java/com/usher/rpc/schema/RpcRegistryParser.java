package com.usher.rpc.schema;

import com.usher.rpc.common.NetcomType;
import com.usher.rpc.common.RegistryType;
import com.usher.rpc.config.RpcRegistryConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class RpcRegistryParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(RpcRegistryConfig.class);
        beanDefinition.setLazyInit(false);

        String registryId = element.getAttribute("id");
        String registryType = element.getAttribute("type");
        String registryAddress = element.getAttribute("address");
        String registryPort = element.getAttribute("port");


        if(StringUtils.isEmpty(registryId)){
            String className = RpcRegistryConfig.class.getName();
            int beanOccurTimes = 2;
            registryId = className;
            while(parserContext.getRegistry().containsBeanDefinition(className)){
                registryId = className + (beanOccurTimes++);
            }
        }else{
            if(parserContext.getRegistry().containsBeanDefinition(registryId)){
                throw new IllegalArgumentException(String.format("<rpc:registry id=\"%s\">, which %s has already been declared before!", registryId, registryId));
            }
        }

        beanDefinition.getPropertyValues().addPropertyValue("registryId", registryId);
        if(!RegistryType.matchRegistryType(registryType)){
            throw new IllegalArgumentException(String.format("<rpc:registry type=\"%s\">, unknown registry type which should be one of \"REDIS,ZOOKEEPER、STANDALONE\"", registryType));
        }
        beanDefinition.getPropertyValues().addPropertyValue("registryType", registryType);

        boolean isStandAloneRegistry = registryType.equals(RegistryType.getRegistryTypeStr(RegistryType.STANDALONE, true));
        if(isStandAloneRegistry){
            if(StringUtils.isEmpty(registryAddress)){
                registryAddress = "localhost";
            }
        }
        if(StringUtils.isEmpty(registryAddress)){
            throw new IllegalArgumentException("registry address cannot be null");
        }
        if(StringUtils.isEmpty(registryPort)){
            throw new IllegalArgumentException("registry port cannot be null");
        }


        beanDefinition.getPropertyValues().addPropertyValue("registryAddress", registryAddress);
        beanDefinition.getPropertyValues().addPropertyValue("registryPort", Integer.parseInt(registryPort));

        parserContext.getRegistry().registerBeanDefinition(registryId, beanDefinition);
        return beanDefinition;
    }
}
