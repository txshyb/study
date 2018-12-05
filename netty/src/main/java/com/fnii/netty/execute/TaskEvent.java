package com.fnii.netty.execute;

import com.google.common.eventbus.Subscribe;
import com.fnii.netty.server.ServerHandler;

/**
 * @author: tangxiaoshuang
 * @date: 2018/9/15 14:34
 * @desc:
 */
public abstract class TaskEvent {

    /**
     * {@link ServerHandler#channelRead}
     *
     * @param msg
     */
    @Subscribe
    public void subscribe(Object msg) {
        try {
            notice(msg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    ;

    public abstract void notice(Object msg);
}
