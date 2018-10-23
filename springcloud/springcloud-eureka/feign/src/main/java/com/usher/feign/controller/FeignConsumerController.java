package com.usher.feign.controller;

import com.usher.feign.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignConsumerController {
    @Autowired
    private UserService userService;

    @GetMapping("/hi")
    public String sayHi(@RequestParam(value = "username") String username){
        return userService.say(username);
    }
}
