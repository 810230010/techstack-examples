package com.usher.rpc.registry.local;

import com.usher.rpc.common.ServiceURL;
import com.usher.rpc.registry.AbstractServiceRegister;
import com.usher.rpc.stub.RpcServiceInvoker;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalServiceRegister extends AbstractServiceRegister {

    public static Map<String, String> localServiceMap = new ConcurrentHashMap();

    public LocalServiceRegister(String address, int port) {
        super(address, port);
    }

    @Override
    public void registerService(Map<String, String> services) {
        if(!CollectionUtils.isEmpty(services)){
            for(Map.Entry<String, String> serviceEntry : services.entrySet()){
                String ifaceName = serviceEntry.getKey();
                String url = serviceEntry.getValue();
                ServiceURL serviceURL = ServiceURL.toServiceURL(url);
                String refClassName = serviceURL.getParamValueFor("ref");
                Class refClass = null;
                try {
                    refClass = Thread.currentThread().getContextClassLoader().loadClass(refClassName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    RpcServiceInvoker.serviceMap.put(ifaceName, refClass.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void stop() {

    }
}
