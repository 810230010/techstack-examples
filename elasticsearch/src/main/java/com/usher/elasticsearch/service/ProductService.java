package com.usher.elasticsearch.service;

import com.usher.elasticsearch.entity.Product;
import com.usher.elasticsearch.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;
    public List<Product> listProduct() {
        List<Product> products = productMapper.selectList(null);
        return products;
    }
}
