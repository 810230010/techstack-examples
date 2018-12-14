package com.usher.rpc.schema;

import com.usher.rpc.config.RpcServiceConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class RpcServiceParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(RpcServiceConfig.class);
        beanDefinition.setLazyInit(false);

        String interfaceName = element.getAttribute("interfaceName");
        String timeout = element.getAttribute("timeout");
        String ifaceImpl = element.getAttribute("class");

        if(StringUtils.isEmpty(interfaceName)){
            throw new IllegalStateException("<rpc:service interface=\"\">, interface cannot be null");
        }
        if(!StringUtils.isEmpty(ifaceImpl)){
            beanDefinition.getPropertyValues().addPropertyValue("ref", ifaceImpl);
        }
        beanDefinition.getPropertyValues().addPropertyValue("timeout", timeout);
        beanDefinition.getPropertyValues().addPropertyValue("interfaceName", interfaceName);

        parserContext.getRegistry().registerBeanDefinition(interfaceName, beanDefinition);
        return beanDefinition;
    }
}
