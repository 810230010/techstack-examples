package com.usher.netty;

import com.usher.nio.demo.FileChannelDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


public class NettyApplicationTests {

    @Test
    public void contextLoads() {
        String srcFile = "E:\\test.txt";
        String desFile = "E:\\test2.txt";
        try {
            FileChannelDemo.copyFile(srcFile, desFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
