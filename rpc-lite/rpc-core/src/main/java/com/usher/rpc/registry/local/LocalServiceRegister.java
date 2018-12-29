package com.usher.rpc.registry.local;

import com.usher.rpc.registry.AbstractServiceRegister;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LocalServiceRegister extends AbstractServiceRegister {

    private static Map<String, String> localServiceMap = new ConcurrentHashMap();

    public LocalServiceRegister(String address, int port) {
        super(address, port);
    }

    @Override
    public void registerService(Map<String, String> services) {
        if(!CollectionUtils.isEmpty(services)){
            for(Map.Entry<String, String> serviceEntry : services.entrySet()){
                String ifaceName = serviceEntry.getKey();
                String url = serviceEntry.getValue();
                localServiceMap.put(ifaceName, url);
            }
        }
    }

    @Override
    public void stop() {

    }
}
