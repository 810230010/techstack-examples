package com.usher.rpc.schema;

import com.usher.rpc.config.RpcRegistryConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class RpcRegistryParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String address = element.getAttribute("address");
        String port = element.getAttribute("port");
        String serializor = element.getAttribute("serializor");
        String connector = element.getAttribute("connector");
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(RpcRegistryConfig.class);
        beanDefinition.setLazyInit(false);

        beanDefinition.getPropertyValues().addPropertyValue("address", address);
        beanDefinition.getPropertyValues().addPropertyValue("port", port);
        beanDefinition.getPropertyValues().addPropertyValue("serializor", serializor);
        beanDefinition.getPropertyValues().addPropertyValue("connector", connector);

        parserContext.getRegistry().registerBeanDefinition(address, beanDefinition);
        parserContext.getRegistry().registerBeanDefinition(port, beanDefinition);
        parserContext.getRegistry().registerBeanDefinition(serializor, beanDefinition);
        parserContext.getRegistry().registerBeanDefinition(connector, beanDefinition);
        return beanDefinition;
    }
}
