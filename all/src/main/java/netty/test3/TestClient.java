package netty.test3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.MarshallingCodeCFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author tangxiaoshuang
 * @date 2018/5/30 9:44
 */
public class TestClient {

    private static Logger logger = LoggerFactory.getLogger(TestClient.class);
    private static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) throws Exception{
        TestClient testClient = new TestClient();
        ChannelFuture channelFuture = testClient.getChannelFuture();
        RequestInfo requestInfo = new RequestInfo("request1","内容1");
        Channel channel = channelFuture.sync().channel();
        channel.writeAndFlush(requestInfo);
        logger.info("主线程结束");
    }


    private Bootstrap bootstrap;
    private ChannelFuture channelFuture;
    public TestClient() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                   //     ch.pipeline().addLast(new ReadTimeoutHandler(5)); //5秒没有收到某客户端消息后则关掉响应通道
                        ch.pipeline().addLast(new MyHandler());
                    }
                });
    }


    private void connect() {
        try {
            this.channelFuture = bootstrap.connect("127.0.0.1",8088).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ChannelFuture getChannelFuture() {
        if(channelFuture == null) {
            connect();
        }
        if(!channelFuture.channel().isActive()) {
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
            logger.info("Client:{}",msg);
            if(msg instanceof ResponseInfo && ((ResponseInfo) msg).getCode() == Code.apply) {
                //3秒后发送一个心跳信息，之后每隔5秒发送一次
                executorService.scheduleWithFixedDelay(new MyThread(ctx),3,5,TimeUnit.SECONDS);
            }
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
}
