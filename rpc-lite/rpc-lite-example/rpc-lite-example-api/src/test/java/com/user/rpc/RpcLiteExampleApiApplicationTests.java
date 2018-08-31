package com.user.rpc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;


public class RpcLiteExampleApiApplicationTests {

    @Test
    public void contextLoads() {
    }

    interface UserService{
        void say();
    }

}
