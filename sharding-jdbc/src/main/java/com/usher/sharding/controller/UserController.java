package com.usher.sharding.controller;

import com.usher.sharding.entity.User;
import com.usher.sharding.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/users/{userId}")
    public Object getUser(@PathVariable Integer userId){
        return userMapper.findByUserId(userId);
    }

}
