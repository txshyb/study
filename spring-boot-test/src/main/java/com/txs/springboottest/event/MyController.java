package com.txs.springboottest.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {


    @Autowired
    EventPublish eventPublish;

    @RequestMapping("event")
    public void event() {
        eventPublish.publish();
    }
}
