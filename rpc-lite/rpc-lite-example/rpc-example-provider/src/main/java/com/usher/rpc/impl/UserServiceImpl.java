package com.usher.rpc.impl;

import com.usher.iface.UserService;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String say() {
        return "Hello World!";
    }

    @Override
    public String introduce() {
        return "hello, i'm rpc.";
    }
}
