package com.usher.service.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/hi")
    public String say(@RequestParam(value = "username") String username){
        if(username.equals("jjp")){
            throw new NullPointerException();
        }
        return "hello" + username;
    }
}
