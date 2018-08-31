package com.usher.rpc.impl;

import com.user.rpc.api.IUserService;
import com.usher.rpc.annotation.RpcService;
import org.springframework.stereotype.Service;

@RpcService(IUserService.class)
@Service
public class UserServiceImpl implements IUserService {
    @Override
    public String sayHi() {
        return "hello world";
    }
}
