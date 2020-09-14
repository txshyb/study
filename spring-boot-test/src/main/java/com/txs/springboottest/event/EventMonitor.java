package com.txs.springboottest.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Async
@Component
public class EventMonitor implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.err.println("EventMonitor :" + Thread.currentThread().getName());
        System.out.println(myEvent.getSource());
    }
}
