package com.fnii.netty.client;

import com.fnii.netty.MarshallingCodeCFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author tangxiaoshuang
 * @date 2018/5/30 9:44
 */
public class TestClient {

    private static Logger logger = LoggerFactory.getLogger(TestClient.class);
    private static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) throws Exception {
        TestClient testClient = new TestClient();
        ChannelFuture channelFuture = testClient.getChannelFuture();
        RequestInfo requestInfo = new RequestInfo("request1", "内容1");
        Channel channel = channelFuture.sync().channel();
        channel.writeAndFlush(requestInfo);
        executorService.scheduleWithFixedDelay(new MyThread2(channel), 3, 10, TimeUnit.SECONDS);
        logger.info("主线程结束");
    }


    private Bootstrap bootstrap;
    private ChannelFuture channelFuture;

    public TestClient() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        ch.pipeline().addLast(new MyHandler());
                    }
                });
    }


    private void connect() {
        try {
            this.channelFuture = bootstrap.connect("127.0.0.1", 8088).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ChannelFuture getChannelFuture() {
        if (channelFuture == null) {
            connect();
        }
        if (!channelFuture.channel().isActive()) {
            connect();
        }
        return channelFuture;
    }


    private static class MyHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            logger.info("开始接收");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            super.channelRead(ctx, msg);
            logger.info("Client:{}", msg);
            Thread.sleep(20);
            ctx.writeAndFlush("{\n" +
                    "\t\"act\": 1,\n" +
                    "\t\"taskId\": \"test\"\n" +
                    "}");
//            if (msg instanceof ResponseInfo && ((ResponseInfo) msg).getCode() == Code.apply) {
//                //3秒后发送一个心跳信息，之后每隔5秒发送一次
//                executorService.scheduleWithFixedDelay(new MyThread(ctx), 3, 5, TimeUnit.SECONDS);
//            }
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            super.channelReadComplete(ctx);
            logger.info("完成接收");
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
            logger.info("client exception");
            ctx.close();
        }


        private class MyThread implements Runnable {
            private ChannelHandlerContext channelHandlerContext;

            public MyThread(ChannelHandlerContext ctx) {
                channelHandlerContext = ctx;
            }

            @Override
            public void run() {
                channelHandlerContext.writeAndFlush("tt");
            }
        }


    }

    private static class MyThread2 implements Runnable {
        private Channel channel;

        public MyThread2(Channel channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
//            channel.writeAndFlush("{\n" +
//                    "\t\"act\": 1,\n" +
//                    "\t\"taskId\": \"1044828747338878976\"\n" +
//                    "}");
        }
    }


}
