package com.usher.rpc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class RpcCoreApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(test());
    }

    public static boolean test(){
        try{
            int a = 1 / 0;
        }catch (ArithmeticException e){
            return false;
        }
        return true;
    }
}
