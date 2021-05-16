package com.ss.springcloud.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping(value = "/payment/zk")
    public String paymentzk() {
        return "springCloud with zookeeper：" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
