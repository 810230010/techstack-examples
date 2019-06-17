package com.usher.elasticsearch.controller;

import com.usher.elasticsearch.common.RestResult;
import com.usher.elasticsearch.service.ProductESService;
import com.usher.elasticsearch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductESService productESService;
    @GetMapping("index/create")
    public Object createIndex(String index){

        productESService.createProductIndex(index);
        return new RestResult();
    }

    @GetMapping("refresh-product")
    public Object refreshProduct(){
        productESService.refreshDBProducts();
        return new RestResult();
    }
}
