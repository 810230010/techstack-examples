package com.usher.elasticsearch;

import com.usher.elasticsearch.config.ElasticSearchConfig;
import com.usher.elasticsearch.service.ProductESService;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.PostConstruct;

@SpringBootApplication
@MapperScan("com.usher.elasticsearch.mapper")
public class ElasticsearchApplication {
    @Autowired
    private ElasticSearchConfig config;
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }

    @PostConstruct
    private void setURL() {

        ProductESService.setClient(new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(config.getEsIp(), config.getEsPort(), config.getEsSchema()))));
    }
}
