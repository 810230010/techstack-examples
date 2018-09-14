package com.usher.rpc.schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class RpcReferenceParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String id= element.getAttribute("id");
        String interfaceName = element.getAttribute("interface");
        String serializor = element.getAttribute("serializor");
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(RpcReference.class);
        beanDefinition.setLazyInit(false);

        beanDefinition.getPropertyValues().addPropertyValue("id", id);
        beanDefinition.getPropertyValues().addPropertyValue("interface", interfaceName);
        beanDefinition.getPropertyValues().addPropertyValue("serializor", serializor);

        parserContext.getRegistry().registerBeanDefinition(interfaceName, beanDefinition);
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        parserContext.getRegistry().registerBeanDefinition(serializor, beanDefinition);
        return beanDefinition;
    }
}
