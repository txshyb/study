package netty.test2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.MarshallingCodeCFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author tangxiaoshuang
 * @date 2018/5/30 9:44
 */
public class TestClient {

    private static Logger logger = LoggerFactory.getLogger(TestClient.class);


    public static void main(String[] args) throws Exception{
        final TestClient testClient = new TestClient();
        final ChannelFuture channelFuture = testClient.getChannelFuture();
        RequestInfo requestInfo = new RequestInfo("request1","内容1");
        RequestInfo requestInfo2 = new RequestInfo("request2","内容2");
        RequestInfo requestInfo3 = new RequestInfo("request3","内容3");
        Channel channel = channelFuture.sync().channel();
        channel.writeAndFlush(requestInfo);
//        TimeUnit.SECONDS.sleep(4);  //不休眠的话 可能write后还没flush时下一次写就写进来了，则这条与下一条Server会当做一条消息
//        channel.writeAndFlush(requestInfo2);
//        TimeUnit.SECONDS.sleep(4);  //不休眠的话 可能write后还没flush时下一次写就写进来了，则这条与下一条Server会当做一条消息
//        channel.writeAndFlush(requestInfo3);

        TimeUnit.SECONDS.sleep(6);
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("isActive？：{}",channelFuture.channel().isActive()); // 6秒后通道关闭
                ChannelFuture channelFuture = testClient.getChannelFuture();   //重新开启通道
                logger.info("isActive？：{}",channelFuture.channel().isActive());
                RequestInfo requestInfo = new RequestInfo("request子线程","内容子线程");
                Channel channel = null;
                try {
                    channel = channelFuture.sync().channel();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.writeAndFlush(requestInfo);
            }
        }).start();
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
    }
}
