package com.ss.springcloud.controller;


import com.ss.springcloud.entities.CommonResult;
import com.ss.springcloud.entities.Payment;
import com.ss.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping(value = "/payment/create")
    public CommonResult create( Payment payment) {
        int result = paymentService.create(payment);
        log.info("*******插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort"+serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*******查询结果：" + payment);

        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort"+serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败,查询ID:"+id, null);
        }
    }

    @GetMapping(value = "/payment/discover")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element:services) {
            log.info("****element:"+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance serviceInstance:instances) {
            log.info(serviceInstance.getServiceId()+"\t"+serviceInstance.getHost()+"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri());
        }
        return discoveryClient;
    }


}
