package com.usher.rpc.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class RpcNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("registry", new RpcRegistryParser());
        registerBeanDefinitionParser("protocol", new RpcProtocolParser());
        registerBeanDefinitionParser("service", new RpcServiceParser());
        registerBeanDefinitionParser("reference", new RpcReferenceParser());
    }
}
