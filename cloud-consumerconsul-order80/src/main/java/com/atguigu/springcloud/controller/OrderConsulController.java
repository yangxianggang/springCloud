package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.http.ResponseEntity;
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


    /**
     *  1.使用Ribbon 实现服务调用 和负载均衡
     *  2.使用ribbon 修改负载均衡的方式
     * @param id
     * @return
     */
    @GetMapping("/queryPaymentRibbon/{id}")
    public CommonResult<Payment> queryPaymentRibbon(@PathVariable("id") Long id) {
        //Ribbon负载均衡 默认采用 轮询方式进行调用
        //若进行特殊化定制的话，自定义配置类不能放在@ComponentScan 所扫描的当前包下以及子包下
        //在主启动类上添加注解 @RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class) 指定负载均衡的方式
        ResponseEntity<CommonResult>entity=restTemplate.getForEntity(INVOKE_URL_CREATE + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }else {
            return new CommonResult<>(444,"22222");
        }
    }

}
