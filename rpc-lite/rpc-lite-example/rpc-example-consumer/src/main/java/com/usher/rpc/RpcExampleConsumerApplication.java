package com.usher.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RpcExampleConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcExampleConsumerApplication.class, args);
    }
}
