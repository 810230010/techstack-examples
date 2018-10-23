package com.usher.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceCaller {
    @Autowired
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "userServiceErr")
    public String callUserService(String username){
        String res = restTemplate.getForObject("http://SERVICE-PROVIDER/hi?username=" + username, String.class);
        return res;
    }

    public String userServiceErr(String username){
        return "user service error";
    }
}
