package org.liuep.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/consumerTest")
    public String consumerTest(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("EUREKA-SERVICE-PAYMENT");
        log.info("address get = " + serviceInstance.getHost());
        String result = restTemplate.getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/test",String.class);
        return result;
    }

    @RequestMapping("/ll")
    public String ll(){
        return restTemplate.getForObject("http://EUREKA-SERVICE-PAYMENT/test",String.class);
    }
}
