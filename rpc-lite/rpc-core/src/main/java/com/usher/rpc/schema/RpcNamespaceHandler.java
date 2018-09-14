package com.usher.rpc.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class RpcNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("service", new RpcServiceParser());
        registerBeanDefinitionParser("registry", new RpcRegistryParser());
        registerBeanDefinitionParser("reference", new RpcReferenceParser());
    }
}
