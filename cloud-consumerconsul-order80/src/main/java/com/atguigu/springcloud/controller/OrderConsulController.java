package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @auther zzyy
 * @create 2020-02-19 16:24
 */
@RestController
@Slf4j
public class OrderConsulController {
    //public static final String INVOKE_URL = "http://consul-provider-payment";
    //单机
    //public static final String INVOKE_URL_CREATE = "http://localhost:8001";
    //集群使用
    public static final String INVOKE_URL_CREATE = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

//    @GetMapping(value = "/consumer/payment/consul")
//    public String paymentInfo()
//    {
//        String result = restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
//        return result;
//    }


    @GetMapping("/crate")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(INVOKE_URL_CREATE + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/queryPayment/{id}")
    public CommonResult<Payment> create(@PathVariable("id") Long id) {
        return restTemplate.getForObject(INVOKE_URL_CREATE + "/payment/get/" + id, CommonResult.class);
    }

}
