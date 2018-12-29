package com.usher.rpc.config.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class RpcNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("application", new RpcApplicationParser());
        registerBeanDefinitionParser("registry", new RpcRegistryParser());
        registerBeanDefinitionParser("protocol", new RpcProtocolParser());
        registerBeanDefinitionParser("service", new RpcServiceParser());
        registerBeanDefinitionParser("reference", new RpcReferenceParser());
    }
}
