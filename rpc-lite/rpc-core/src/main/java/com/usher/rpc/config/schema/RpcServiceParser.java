package com.usher.rpc.config.schema;

import com.usher.rpc.common.LoadbalanceType;
import com.usher.rpc.config.RpcProtocolConfig;
import com.usher.rpc.config.RpcRegistryConfig;
import com.usher.rpc.config.RpcServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
@Slf4j
public class RpcServiceParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(RpcServiceConfig.class);
        rootBeanDefinition.setLazyInit(false);

        String serviceId = element.getAttribute("id");
        String interfaceName = element.getAttribute("interface");
        String timeout = element.getAttribute("timeout");
        String ifaceImpl = element.getAttribute("class");
        String ref = element.getAttribute("ref");
        String loadbalance = element.getAttribute("loadbalance");

        if(StringUtils.isEmpty(serviceId)){
            String className = RpcServiceConfig.class.getName();
            serviceId = className;
            int beanOccurTimes = 2;
            while(parserContext.getRegistry().containsBeanDefinition(serviceId)){
                serviceId = className + (beanOccurTimes++);
            }
        }else{
            if(parserContext.getRegistry().containsBeanDefinition(serviceId)){
                throw new IllegalArgumentException(String.format("<rpc:service id=\"%s\">, which \"%s\" has already declared before!"));
            }
        }
        rootBeanDefinition.getPropertyValues().addPropertyValue("id", serviceId);

        if(StringUtils.isEmpty(interfaceName)){
            throw new IllegalStateException("<rpc:service interface=\"\">, interface cannot be null");
        }
        rootBeanDefinition.getPropertyValues().addPropertyValue("ifaceName", interfaceName);

        if(!StringUtils.isEmpty(ifaceImpl)){
            rootBeanDefinition.getPropertyValues().addPropertyValue("ref", ifaceImpl);
        }else if(!StringUtils.isEmpty(ref)){
            if(parserContext.getRegistry().containsBeanDefinition(ref)){
                BeanDefinition refBeanDefition = parserContext.getRegistry().getBeanDefinition(ref);
                String className = refBeanDefition.getBeanClassName();
                rootBeanDefinition.getPropertyValues().addPropertyValue("ref", className);
            }else {
                throw new IllegalArgumentException(String.format("<rpc:service ref=\"%s\">, which %s is an unregistered bean!", ref, ref));
            }
        }else{
            throw new IllegalArgumentException("<rpc service class=\"\" ref=\"\">, either class or ref cannot be empty!");
        }
        boolean isValidLoadbalanceType = LoadbalanceType.matchLoadbalanceType(loadbalance);
        if(isValidLoadbalanceType){
            rootBeanDefinition.getPropertyValues().addPropertyValue("loadbalance", loadbalance);
        }else{
            throw new IllegalArgumentException(
                    String.format("<rpc:service loadbalance=\"%s\">, unknown loadbalance type which should be one of \"RANDOM, ROUND-ROBIN, CONSISTENT_HASH\"", loadbalance));
        }

        rootBeanDefinition.getPropertyValues().addPropertyValue("timeout", Long.parseLong(timeout));

        //get protocol

        for(String beanDefinitionName : parserContext.getRegistry().getBeanDefinitionNames()){
            BeanDefinition beanDefinition = parserContext.getRegistry().getBeanDefinition(beanDefinitionName);

            String beanClassName = beanDefinition.getBeanClassName();
            if(beanClassName.equals(RpcProtocolConfig.class.getName())){
                PropertyValue netcomProperty = beanDefinition.getPropertyValues().getPropertyValue("netcom");
                PropertyValue serializorProperty = beanDefinition.getPropertyValues().getPropertyValue("serializor");
                PropertyValue portProperty = beanDefinition.getPropertyValues().getPropertyValue("servicePort");
                if(netcomProperty != null  && serializorProperty != null && portProperty != null){
                    Object netcom = netcomProperty.getValue();
                    Object serializor = serializorProperty.getValue();
                    Object port = portProperty.getValue();
                    log.info("网络组件: {}, 序列化方式: {}", netcom, serializor);
                    rootBeanDefinition.getPropertyValues().addPropertyValue("netcom", netcom);
                    rootBeanDefinition.getPropertyValues().addPropertyValue("serializor", serializor);
                    rootBeanDefinition.getPropertyValues().addPropertyValue("servicePort", port);
                }
                break;
            }

        }

        //get registry
        for(String beanDefinitionName : parserContext.getRegistry().getBeanDefinitionNames()){
            BeanDefinition beanDefinition = parserContext.getRegistry().getBeanDefinition(beanDefinitionName);

            String beanClassName = beanDefinition.getBeanClassName();
            if(beanClassName.equals(RpcRegistryConfig.class.getName())){
                PropertyValue addressProperty = beanDefinition.getPropertyValues().getPropertyValue("registryAddress");
                PropertyValue portProperty = beanDefinition.getPropertyValues().getPropertyValue("registryPort");
                PropertyValue typeProperty = beanDefinition.getPropertyValues().getPropertyValue("registryType");
                if(addressProperty != null  && portProperty != null && typeProperty != null){
                    Object address= addressProperty.getValue();
                    Object port = portProperty.getValue();
                    Object type = typeProperty.getValue();
                    log.info("注册地址: {}, 注册地址端口: {}, 注册中心类型: {}", address, port, type);
                    rootBeanDefinition.getPropertyValues().addPropertyValue("registryAddress", address);
                    rootBeanDefinition.getPropertyValues().addPropertyValue("registryPort", port);
                    rootBeanDefinition.getPropertyValues().addPropertyValue("registryType", type);
                }
                break;
            }

        }

        parserContext.getRegistry().registerBeanDefinition(serviceId, rootBeanDefinition);
        return rootBeanDefinition;
    }

}
