[![Build status](https://travis-ci.org/iluwatar/java-design-patterns.svg?branch=master)](https://travis-ci.org/iluwatar/java-design-patterns)
[![License MIT](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/iluwatar/java-design-patterns/master/LICENSE.md)
# techstack-examples
各个技术的使用demo以及手写一些框架，比如rpc。
### 项目结构介绍
``` 
algorithm  算法和数据机构

common 
    ---design 设计模式

concurrency  并发

dubbo dubbo运行demo
    ---dubbo-consumer  消费者
    ---dubbo-provider  生产者

elasticsearch es运行demo

kafka  kafka运行demo

netty  netty运行demo

rpc-lit  仿写rpc
    ---rpc-core     具体实现逻辑
    ---rpc-lite-example  运行demo

sharding-jdbc   sharding-jdbc运行demo

spring-cloud  spring-cloud运行demo
    ---eureka-server  注册中心
    ---feign    接口调用服务  
    ---ribbon   http调用服务
    ---service-provider 服务提供者
    ---zuul  服务网关

zookeeper  zookeeper运行demo

//TODO
mongodb  
```
