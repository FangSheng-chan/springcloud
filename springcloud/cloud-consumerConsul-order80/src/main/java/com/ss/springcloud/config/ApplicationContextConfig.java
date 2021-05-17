package com.ss.springcloud.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * applicationContext.xml =>  <bean id="" class = "">
 * @author ss
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    /**
     * 使用 @LoadBalanced注解注释赋予 RestTemplate 负载均衡能力
     */
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
