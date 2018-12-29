package com.usher.consumer.controller;

import com.usher.consumer.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @Autowired
    private ReferenceService referenceService;
    @RequestMapping("/test")
    public String test(){
        return referenceService.test();
    }
}
