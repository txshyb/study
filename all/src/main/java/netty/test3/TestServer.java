package netty.test3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import netty.MarshallingCodeCFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangxiaoshuang
 * @date 2018/5/30 9:25
 *
 *
 * netty  实现心跳
 *
 *
 */
public class TestServer {

    private static Logger logger = LoggerFactory.getLogger(TestServer.class);

    public static void main(String[] args) {

        EventLoopGroup pGroup = new NioEventLoopGroup(); //1.第一个线程组是用于接收Client端连接的
        EventLoopGroup cGroup = new NioEventLoopGroup();   //2.第二个线程组是用于实际的业务处理的
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(pGroup,cGroup)  //绑定两个线程池
                .channel(NioServerSocketChannel.class) //指定NIO的模式，如果是客户端就是NioSocketChannel
                .option(ChannelOption.SO_BACKLOG, 1024)//TCP的缓冲区设置
                .option(ChannelOption.SO_SNDBUF, 32*1024)//设置发送缓冲的大小
                .option(ChannelOption.SO_RCVBUF, 32*1024)//设置接收缓冲区大小
                .option(ChannelOption.SO_KEEPALIVE, true)//保持连续
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //设置Marshalling的编码和解码
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        ch.pipeline().addLast(new MyHandler());  //自定义逻辑处理
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.bind(8088).sync();//绑定端口
            channelFuture.channel().closeFuture().sync(); //等待关闭(程序阻塞在这里等待客户端请求)

            pGroup.shutdownGracefully();//关闭线程
            cGroup.shutdownGracefully();//关闭线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class MyHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            logger.info("Server开始接收");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            super.channelRead(ctx, msg);
            logger.info("Server:{}",msg);
            if(msg instanceof RequestInfo) {  //如果是请求连接，则返回响应
                ResponseInfo responseInfo = new ResponseInfo(Code.apply);
                ctx.writeAndFlush(responseInfo);
            }
            //不是请求连接，则为心跳，打印心跳信息
            logger.info("心跳：",msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            super.channelReadComplete(ctx);
            logger.info("Server完成接收");
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
            logger.info("server exception");
            ctx.close();
        }
    }
}
