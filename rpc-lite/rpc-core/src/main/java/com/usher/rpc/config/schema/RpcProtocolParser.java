package com.usher.rpc.config.schema;

import com.usher.rpc.common.NetcomType;
import com.usher.rpc.common.SerializorType;
import com.usher.rpc.config.RpcProtocolConfig;
import com.usher.rpc.config.RpcReferenceConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class RpcProtocolParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(RpcProtocolConfig.class);
        beanDefinition.setLazyInit(false);
        //id,netcom,serializor
        String netcom = element.getAttribute("netcom");
        String serializor = element.getAttribute("serializor");
        String protocolId = element.getAttribute("id");
        String servicePort = element.getAttribute("port");
        if(StringUtils.isEmpty(protocolId)){
            String className = RpcProtocolConfig.class.getName();
            protocolId = className;
            int sameNameBeanOccurTimes = 2;
            while(parserContext.getRegistry().containsBeanDefinition(protocolId)){
                protocolId = className + (sameNameBeanOccurTimes++);
            }
            beanDefinition.getPropertyValues().addPropertyValue("id", protocolId);
        }else{
            if(parserContext.getRegistry().containsBeanDefinition(protocolId)){
                throw new IllegalArgumentException(String.format("<rpc:protocol id=\"%s\">, which \"%s\" has already declared before!"));
            }
        }
        
        if(NetcomType.matchNetcomType(netcom)) {
            beanDefinition.getPropertyValues().addPropertyValue("netcom", netcom);
        }else {
            throw new IllegalArgumentException(String.format("<rpc:protocol netcom=\"%s\">, unknown netcom type which should be one of \"JETTY, NETTY, MINA\"", netcom));
        }

        if(SerializorType.matchSerializorType(serializor)){
            beanDefinition.getPropertyValues().addPropertyValue("serializor", serializor);
        }else {
            throw new IllegalArgumentException(String.format("<rpc:protocol serializor=\"%s\">, unknown serializor type which should be one of \"HESSIAN,JSON,KRYO\""));
        }
        beanDefinition.getPropertyValues().addPropertyValue("servicePort", Integer.parseInt(servicePort));
        parserContext.getRegistry().registerBeanDefinition(protocolId, beanDefinition);
        return beanDefinition;
    }
}
