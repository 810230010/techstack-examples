package com.usher.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RpcExampleProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcExampleProviderApplication.class, args);
    }
}
