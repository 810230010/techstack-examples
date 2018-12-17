package com.usher.rpc.schema;

import com.usher.rpc.common.LoadbalanceType;
import com.usher.rpc.config.RpcServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
@Slf4j
public class RpcServiceParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(RpcServiceConfig.class);
        beanDefinition.setLazyInit(false);

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
        beanDefinition.getPropertyValues().addPropertyValue("id", serviceId);

        if(StringUtils.isEmpty(interfaceName)){
            throw new IllegalStateException("<rpc:service interface=\"\">, interface cannot be null");
        }
        beanDefinition.getPropertyValues().addPropertyValue("ifaceName", interfaceName);

        if(!StringUtils.isEmpty(ifaceImpl)){
            beanDefinition.getPropertyValues().addPropertyValue("ref", ifaceImpl);
        }else if(!StringUtils.isEmpty(ref)){
            if(parserContext.getRegistry().containsBeanDefinition(ref)){
                BeanDefinition refBeanDefition = parserContext.getRegistry().getBeanDefinition(ref);
                String className = refBeanDefition.getBeanClassName();
                beanDefinition.getPropertyValues().addPropertyValue("ref", className);
            }else {
                throw new IllegalArgumentException(String.format("<rpc:service ref=\"%s\">, which %s is an unregistered bean!", ref, ref));
            }
        }else{
            throw new IllegalArgumentException("<rpc service class=\"\" ref=\"\">, either class or ref cannot be empty!");
        }
        boolean isValidLoadbalanceType = LoadbalanceType.matchLoadbalanceType(loadbalance);
        if(isValidLoadbalanceType){
            beanDefinition.getPropertyValues().addPropertyValue("loadbalance", loadbalance);
        }else{
            throw new IllegalArgumentException(
                    String.format("<rpc:service loadbalance=\"%s\">, unknown loadbalance type which should be one of \"RANDOM, ROUND-ROBIN, CONSISTENT_HASH\"", loadbalance));
        }

        beanDefinition.getPropertyValues().addPropertyValue("timeout", Long.parseLong(timeout));

        //get protocol

        parserContext.getRegistry().registerBeanDefinition(serviceId, beanDefinition);
        return beanDefinition;
    }
}
