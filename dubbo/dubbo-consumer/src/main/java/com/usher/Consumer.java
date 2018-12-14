package com.usher;

import com.usher.provider.iface.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        classPathXmlApplicationContext.start();
        UserService userService = (UserService) classPathXmlApplicationContext.getBean("userService");
        System.out.println(userService.greet("Jang Usher"));
    }
}
