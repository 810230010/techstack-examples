package com.usher.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.usher.provider.iface.DemoService;
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String say() {
        return "hello";
    }
}
