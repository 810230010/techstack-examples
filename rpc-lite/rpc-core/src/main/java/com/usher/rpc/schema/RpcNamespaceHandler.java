package com.usher.rpc.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.xml.sax.helpers.NamespaceSupport;

public class RpcNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("service", new RpcBeanDefinitionParser());
        registerBeanDefinitionParser("registry", new RpcBeanDefinitionParser());
        registerBeanDefinitionParser("reference", new RpcBeanDefinitionParser());
    }
}
