package com.example.eureka_feign_client;

import com.example.eureka_feign_client.feign.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    private EurekaClientFeign eurekaClientFeign;

    @GetMapping("/hi")
    public Object hi(String name) {
        return eurekaClientFeign.hi(name);
    }
}
