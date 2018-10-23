package com.usher.feign.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * fallback:服务降级
 */
@FeignClient(name = "service-provider", fallback = UserServiceFallBack.class)
public interface UserService {
    @GetMapping("/hi")
    String say(@RequestParam(value = "username") String username);


}
