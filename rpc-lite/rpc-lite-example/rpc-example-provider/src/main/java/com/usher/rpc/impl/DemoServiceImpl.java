package com.usher.rpc.impl;

import com.usher.iface.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String say() {
        return "hello world";
    }
}
