package com.usher.rpc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RpcExampleProviderApplicationTests {

    @Test
    public void contextLoads() {
        String a = "/aa";
        StringBuilder sb = new StringBuilder(a);
        System.out.println(sb.deleteCharAt(0).toString());
    }

}
