package com.example.eureka_ribbon_client.controller;

import com.example.eureka_ribbon_client.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {
    @Autowired
    private RibbonService ribbonService;

    @GetMapping("/hi1")
    public Object hi1(@RequestParam String name) {
        return ribbonService.hi(name);
    }

    //版本太低 没有该接口实现
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/hi2")
    public Object hi2() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        return serviceInstance.getHost() + serviceInstance.getPort();
    }
}
