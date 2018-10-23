package com.usher.ribbon.controller;

import com.usher.ribbon.service.UserServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserServiceCaller userServiceCaller;
    @RequestMapping("/hi")
    public String hi(String username){
        return userServiceCaller.callUserService(username);
    }
}
