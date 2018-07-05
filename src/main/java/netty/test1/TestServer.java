package netty.test1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import netty.MarshallingCodeCFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangxiaoshuang
 * @date 2018/5/30 9:25
 */
public class TestServer {

    private static Logger logger = LoggerFactory.getLogger(TestServer.class);

    public static void main(String[] args) {
        EventLoopGroup pGroup = new NioEventLoopGroup();
        EventLoopGroup cGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(pGroup,cGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_BACKLOG,1)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        ch.pipeline().addLast(new MyHandler());
                    }
                });
//                .option(ChannelOption.SO_BACKLOG, 100)
//                .handler(new LoggingHandler(LogLevel.INFO))
        try {
            ChannelFuture channelFuture = bootstrap.bind(8088).sync();
            channelFuture.channel().closeFuture().sync();
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
            ctx.writeAndFlush("hi client");
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            super.channelReadComplete(ctx);
            logger.info("Server完成接收");
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }
    }
}