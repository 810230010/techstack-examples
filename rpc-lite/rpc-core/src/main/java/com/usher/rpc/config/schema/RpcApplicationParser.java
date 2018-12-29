package com.usher.rpc.config.schema;

import com.usher.rpc.common.NetcomType;
import com.usher.rpc.common.SerializorType;
import com.usher.rpc.config.RpcApplicationConfig;
import com.usher.rpc.config.RpcProtocolConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class RpcApplicationParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(RpcApplicationConfig.class);
        beanDefinition.setLazyInit(false);

        String applicationId = element.getAttribute("id");
        String applicationName = element.getAttribute("name");

        if(StringUtils.isEmpty(applicationId)){
            String className = RpcApplicationConfig.class.getName();
            applicationId = className;
            int sameNameBeanOccurTimes = 2;
            while(parserContext.getRegistry().containsBeanDefinition(applicationId)){
                applicationId = className + (sameNameBeanOccurTimes++);
            }
            beanDefinition.getPropertyValues().addPropertyValue("applicationId", applicationId);
        }else{
            if(parserContext.getRegistry().containsBeanDefinition(applicationId)){
                throw new IllegalArgumentException(String.format("<rpc:protocol id=\"%s\">, which \"%s\" has already declared before!"));
            }
        }

        beanDefinition.getPropertyValues().addPropertyValue("applicationName", applicationName);
        parserContext.getRegistry().registerBeanDefinition(applicationId, beanDefinition);
        return beanDefinition;
    }
}
