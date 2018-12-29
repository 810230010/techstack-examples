package com.usher.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.usher.provider.iface.DemoService;
import org.springframework.stereotype.Service;

@Service
public class ReferenceService {
    @Reference
    DemoService demoService;
    public String test(){
        return demoService.say();
    }


}
