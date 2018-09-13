package com.usher.rpc.impl;

import com.usher.iface.UserService;
import com.usher.rpc.annotation.RpcService;
import org.springframework.stereotype.Service;

@Service
@RpcService(UserService.class)
public class UserServiceImpl implements UserService {
    @Override
    public String say() {
        return "Hello World!";
    }
}