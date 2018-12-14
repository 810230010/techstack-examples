package com.usher.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.usher.provider.iface.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@ImportResource({"classpath:dubbo-consumer.xml"})
public class ConsumerApplication{

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        classPathXmlApplicationContext.start();
        UserService userService = (UserService) classPathXmlApplicationContext.getBean("userService");
        System.out.println(userService.greet("Jang"));
    }
}
