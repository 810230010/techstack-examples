package com.usher.elasticsearch;

import com.usher.elasticsearch.entity.Product;
import com.usher.elasticsearch.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {
    @Autowired
    private ProductMapper productMapper;
    @Test
    public void contextLoads() {
        List<Product> list = productMapper.selectList(null);
        System.out.println(list);
    }

}
