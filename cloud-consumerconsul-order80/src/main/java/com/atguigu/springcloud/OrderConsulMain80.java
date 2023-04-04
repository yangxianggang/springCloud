package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @auther zzyy
 * @create 2020-02-19 16:22
 */
@SpringBootApplication
@EnableEurekaClient
 public class OrderConsulMain80
{
    public static void main(String[] args) {
            SpringApplication.run(OrderConsulMain80.class, args);
    }
}
