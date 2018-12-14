package com.usher.provider.impl;

import com.usher.provider.iface.UserService;
import org.springframework.stereotype.Service;

public class UserServiceImpl implements UserService {
    @Override
    public String greet(String username) {
        return "hello " + username;
    }
}
