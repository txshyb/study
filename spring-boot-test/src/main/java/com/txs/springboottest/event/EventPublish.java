package com.txs.springboottest.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class EventPublish {

    //使用ApplicationEventPublisher 广播消息
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    //内部使用SimpleApplicationEventMulticaster来实现广播消息
    //默认情况下是同步的但是可以给其配置线程池 则消息的处理变成异步（事件监听类上加@Async也可以，但是既然可以配置线程池直接使用线程池是最好的）
    //applicationEventMulticaster.setTaskExecutor(new ThreadPoolExecutor(10,10, 10,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10)));
    @Autowired
    private SimpleApplicationEventMulticaster applicationEventMulticaster;

    public void publish() {
        //配置线程池
        applicationEventMulticaster.setTaskExecutor(new ThreadPoolExecutor(10,10, 10,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10)));
        System.err.println("EventPublish :" + Thread.currentThread().getName());
        MyMessage myMessage = new MyMessage();
        myMessage.setName("ttt");
        applicationEventPublisher.publishEvent(new MyEvent(myMessage));
    }
}
