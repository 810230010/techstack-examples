package com.usher.feign.config;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class HystrixConfig {
    @Bean
    @Scope("prototype")
    public Feign.Builder feignHystrixBuilder() {
        return Feign.builder();
    }

}
