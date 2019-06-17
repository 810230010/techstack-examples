package com.usher.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


public class KafkaApplicationTests {

    @Test
    public void contextLoads() {
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        l2.add("a1");
        BeanUtils.copyProperties(l1, l2);
        System.out.println(l2);
    }

}
