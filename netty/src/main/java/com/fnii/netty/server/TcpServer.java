package com.fnii.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author tangxiaoshuang
 * @date 2018/5/30 9:25
 * netty  实现心跳
 */
@Component
public class TcpServer {

    @Autowired
    private ChannelInitializer channelInitializer;

    @Value("${netty.port}")
    private int port;

    private LogLevel logLevel;

    private Channel serverChannel;

    public TcpServer() {
        logLevel = LogLevel.INFO;
        //启动服务端
        new Thread(new ServerStart()).start();
    }

    public TcpServer(LogLevel logLevel) {
        this.logLevel = logLevel;
        new Thread(new ServerStart()).start();

    }

    public Channel getServerChannel() {
        return serverChannel;
    }

    @PostConstruct
    public void start() {

    }

    @PreDestroy
    public void stop() throws Exception {
        serverChannel.close();
        serverChannel.parent().close();
    }

    public class ServerStart implements Runnable {

        @Override
        public void run() {
            EventLoopGroup pGroup = new NioEventLoopGroup(1); //1.第一个线程组是用于接收Client端连接的 该线程组线程数设为1
            EventLoopGroup cGroup = new NioEventLoopGroup();   //2.第二个线程组是用于实际的业务处理的
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(pGroup, cGroup)  //绑定两个线程池
                    .channel(NioServerSocketChannel.class) //指定NIO的模式，如果是客户端就是NioSocketChannel
                    .option(ChannelOption.SO_BACKLOG, 1024)//TCP的缓冲区设置
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)//设置发送缓冲的大小
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)//设置接收缓冲区大小
                    .option(ChannelOption.SO_KEEPALIVE, true)//保持连续
                    .handler(new LoggingHandler(logLevel))
                    .childHandler(channelInitializer);
            try {
                ChannelFuture channelFuture = bootstrap.bind(port).sync();//绑定端口
                serverChannel = channelFuture.sync().channel();
                serverChannel.closeFuture().sync().channel(); //等待关闭(程序阻塞在这里等待客户端请求)

                pGroup.shutdownGracefully();//关闭线程
                cGroup.shutdownGracefully();//关闭线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
