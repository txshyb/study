package netty.test1;

import com.sun.xml.internal.ws.api.pipe.PipelineAssembler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.util.concurrent.EventExecutorGroup;
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

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        ch.pipeline().addLast(new MyHandler());
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",8088);
        Channel channel;
        try {
            channel = channelFuture.sync().channel();
            channel.writeAndFlush("H换货单号");
            TimeUnit.SECONDS.sleep(1);
            channel.writeAndFlush("dsdf但是");
            TimeUnit.SECONDS.sleep(1);
            channel.writeAndFlush("递四方速递");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
        }
    }
}
