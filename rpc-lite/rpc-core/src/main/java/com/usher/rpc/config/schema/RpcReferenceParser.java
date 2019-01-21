package com.usher.rpc.config.schema;

import com.usher.rpc.config.RpcReferenceConfig;
import com.usher.rpc.config.RpcRegistryConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
@Slf4j
public class RpcReferenceParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String id= element.getAttribute("id");

        long timeout = Long.valueOf(element.getAttribute("timeout"));
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(RpcReferenceConfig.class);
        beanDefinition.setLazyInit(false);

        if(StringUtils.isEmpty(id)){
            throw new IllegalArgumentException("<rpc:reference id=\"%s\">, id cannot be empty!");
        }
        if(parserContext.getRegistry().containsBeanDefinition(id)){
            throw new IllegalArgumentException("<rpc:reference id=\"%s\">, which %s has been declared before!");
        }
        beanDefinition.getPropertyValues().addPropertyValue("id", id);

        beanDefinition.getPropertyValues().addPropertyValue("timeout", timeout);

        for(String beanDefinitionName : parserContext.getRegistry().getBeanDefinitionNames()){
            BeanDefinition beanDef = parserContext.getRegistry().getBeanDefinition(beanDefinitionName);
            String beanName = beanDef.getBeanClassName();
            if(StringUtils.isEmpty(beanName)){
                continue;
            }
            if(beanName.equals(RpcRegistryConfig.class.getName())){
                PropertyValue addressProperty = beanDef.getPropertyValues().getPropertyValue("registryAddress");
                PropertyValue portProperty = beanDef.getPropertyValues().getPropertyValue("registryPort");
                PropertyValue typeProperty = beanDef.getPropertyValues().getPropertyValue("registryType");
                if(addressProperty != null  && portProperty != null && typeProperty != null){
                    Object address= addressProperty.getValue();
                    Object port = portProperty.getValue();
                    Object type = typeProperty.getValue();
                    log.info("注册地址: {}, 注册地址端口: {}, 注册中心类型: {}", address, port, type);
                    beanDefinition.getPropertyValues().addPropertyValue("registryAddress", address);
                    beanDefinition.getPropertyValues().addPropertyValue("registryPort", port);
                    beanDefinition.getPropertyValues().addPropertyValue("registryType", type);
                }
                break;
            }
        }

        String ref = element.getAttribute("ref");
        String ifaceName = element.getAttribute("interface");
        if(StringUtils.isEmpty(ref) && StringUtils.isEmpty(ifaceName)){
            throw new IllegalArgumentException(String.format("<rpc:reference ref=\"\" interface=\"\">, ref and interface cannot be both empty!", ref));
        }
        if(!StringUtils.isEmpty(ref)){
            if(!parserContext.getRegistry().containsBeanDefinition(ref)){
                throw new IllegalArgumentException(String.format("<rpc:reference ref=\"%s\">, there is no such spring bean!", ref));
            }
            Object refereceBean = new RuntimeBeanReference(ref);
            beanDefinition.getPropertyValues().addPropertyValue("ref", refereceBean);
        }else {
            beanDefinition.getPropertyValues().addPropertyValue("ifaceName", ifaceName);
        }

        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);

        return beanDefinition;
    }
}
