package com.usher.elasticsearch.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
public class ElasticSearchConfig {
    @Value("${es.ip}")
    private String esIp;
    @Value("${es.port}")
    private int esPort;
    private String esSchema;
}
