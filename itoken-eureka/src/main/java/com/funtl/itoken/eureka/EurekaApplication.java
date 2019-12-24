package com.funtl.itoken.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
    //服务注册与发现中心
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}