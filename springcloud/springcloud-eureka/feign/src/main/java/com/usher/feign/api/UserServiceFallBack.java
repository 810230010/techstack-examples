package com.usher.feign.api;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 服务降级
 */
@Component
public class UserServiceFallBack implements UserService {


    @Override
    public String say(@RequestParam(value = "username") String username) {
        return "errorrrrrrrrrrr..";
    }
}
