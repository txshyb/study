package com.fnii.netty.execute;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: tangxiaoshuang
 * @date: 2018/9/15 14:28
 * @desc:
 */
@Component
public class ClientEvent {

    private EventBus eventBus;

    @Autowired
    private TaskEvent taskEvent;

    @PostConstruct
    public void init() {
        eventBus = new EventBus();
        eventBus.register(taskEvent);
    }

    public void notice(Object msg) {
        eventBus.post(msg);
    }
}
