package com.usher.rpc.impl;

import com.usher.iface.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String say() {
        return "hello world";
    }
}
