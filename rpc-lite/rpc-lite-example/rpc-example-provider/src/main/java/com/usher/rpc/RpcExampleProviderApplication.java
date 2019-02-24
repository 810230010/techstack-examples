package com.usher.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ImportResource(locations = {"classpath:rpc-provider.xml"})
public class RpcExampleProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RpcExampleProviderApplication.class, args);
    }
}
