package com.txs.springboottest.event;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {

    public MyEvent(MyMessage myMessage) {
        super(myMessage);
    }
}
