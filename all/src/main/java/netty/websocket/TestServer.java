package netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import netty.MarshallingCodeCFactory;
import netty.test3.Code;
import netty.test3.RequestInfo;
import netty.test3.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestHandler;

/**
 * @author tangxiaoshuang
 * @date 2018/5/30 9:25
 * <p>
 * <p>
 * netty  实现心跳
 */
public class TestServer {

    private static Logger logger = LoggerFactory.getLogger(TestServer.class);

    public static void main(String[] args) {

        EventLoopGroup pGroup = new NioEventLoopGroup(); //1.第一个线程组是用于接收Client端连接的
        EventLoopGroup cGroup = new NioEventLoopGroup();   //2.第二个线程组是用于实际的业务处理的
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(pGroup, cGroup)  //绑定两个线程池
                .channel(NioServerSocketChannel.class) //指定NIO的模式，如果是客户端就是NioSocketChannel
                .option(ChannelOption.SO_BACKLOG, 1024)//TCP的缓冲区设置
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)//设置发送缓冲的大小
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)//设置接收缓冲区大小
                .option(ChannelOption.SO_KEEPALIVE, true)//保持连续
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new HttpServerCodec());
                        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(new WebSocketServerProtocolHandler("/test"));
                        pipeline.addLast(new TextWebSocketFrameHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.bind(8082).sync();//绑定端口
            channelFuture.channel().closeFuture().sync(); //等待关闭(程序阻塞在这里等待客户端请求)

            pGroup.shutdownGracefully();//关闭线程
            cGroup.shutdownGracefully();//关闭线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>  {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            Channel incoming = ctx.channel();
            System.out.println("Client:" + incoming.remoteAddress());
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
            System.out.println("msg:" + msg.text());
                ctx.writeAndFlush(new TextWebSocketFrame("来自服务端: "));
        }
    }
}
