package com.fnii.netty.server;

import com.fnii.netty.execute.ClientEvent;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fnii.netty.execute.TaskEvent;

import java.util.concurrent.*;

/**
 * @author: tangxiaoshuang
 * @date: 2018/9/15 10:08
 * @desc:
 */
@Component
/**
 * 共享变量
 */
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(TcpServer.class);

    @Autowired
    private ClientEvent clientEvent;

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static Executor executor;

    static {
        executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

//        logger.info("Server开始接收");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        /**
         * {@link TaskEvent#notice}
         */
        //因为EventLoop是单线程的，自定义业务里耗时操作会堵塞Channel,所以此处用自定义线程池处理业务
        // 但是使用SimpleChannelInboundHandler时候要注意其释放内存操作ReferenceCountUtil.release(msg);会引起线程池内对象已被清除
        executor.execute(() -> {
            clientEvent.notice(msg);
        });
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
//        logger.info("Server完成接收");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        channelGroup.add(ctx.channel());
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        channelGroup.remove(ctx.channel()); //ChannelGroup 在客户端断开时，会自动remove 其实这里可以不调用
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        logger.error("server exception", cause);
        ctx.close();
    }

}
