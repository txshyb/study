package com.example.eureka_ribbon_client.service;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    public Object hi(String name) {
        return restTemplate.getForObject("http://eureka-client/hi?name=" +  name,String.class);
    }
}
