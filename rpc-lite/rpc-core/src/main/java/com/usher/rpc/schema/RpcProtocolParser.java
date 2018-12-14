package com.usher.rpc.schema;

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
    public static final String DEFAULT_PROTOCOL_ID = "protocal";
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(RpcProtocolConfig.class);
        beanDefinition.setLazyInit(false);

        String netcom = element.getAttribute("netcom");
        String serializor = element.getAttribute("serializor");
        String protocolId = element.getAttribute("id");

        if(StringUtils.isEmpty(protocolId)){
            int sameNameBeanOccurTimes = 2;
            while(parserContext.getRegistry().containsBeanDefinition(protocolId)){
                protocolId += (sameNameBeanOccurTimes++);
            }
            beanDefinition.getPropertyValues().addPropertyValue("id", protocolId);
        }else{
            if(parserContext.getRegistry().containsBeanDefinition(protocolId)){
                throw new IllegalArgumentException("duplicate bean id with protocal id");
            }
        }
        
        if(NetcomType.matchNetcomType(netcom)) {
            beanDefinition.getPropertyValues().addPropertyValue("netcom", netcom);
        }else {
            throw new IllegalArgumentException("unknown netcom type which should be one of \"NETTY,JETTY,MINA\"");
        }
        
        if(SerializorType.matchSerializorType(serializor)){
            beanDefinition.getPropertyValues().addPropertyValue("serializor", serializor);
        }else {
            throw new IllegalArgumentException("unknown serializor type which should be one of \"HESSIAN,JSON,KRYO,PROTOSBUFF\"");
        }
        
        parserContext.getRegistry().registerBeanDefinition(protocolId, beanDefinition);
        return beanDefinition;
    }
}
