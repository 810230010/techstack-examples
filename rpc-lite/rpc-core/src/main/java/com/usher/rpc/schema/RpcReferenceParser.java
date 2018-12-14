package com.usher.rpc.schema;

import com.usher.rpc.config.RpcReferenceConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class RpcReferenceParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String id= element.getAttribute("id");
        String interfaceName = element.getAttribute("interface");
        long timeout = Long.valueOf(element.getAttribute("timeout"));
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(RpcReferenceConfig.class);
        beanDefinition.setLazyInit(false);

        beanDefinition.getPropertyValues().addPropertyValue("id", id);
        beanDefinition.getPropertyValues().addPropertyValue("ifaceName", interfaceName);
        beanDefinition.getPropertyValues().addPropertyValue("timeout", timeout);

        parserContext.getRegistry().registerBeanDefinition(interfaceName, beanDefinition);
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        parserContext.getRegistry().registerBeanDefinition(timeout+"", beanDefinition);
        return beanDefinition;
    }
}
