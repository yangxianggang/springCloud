package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @auther zzyy
 * @create 2020-02-18 23:44
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7003
{
    public static void main(String[] args) {
            SpringApplication.run(EurekaMain7003.class, args);
    }
}
