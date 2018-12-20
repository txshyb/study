package com.fnii.netty.server;

import com.fnii.netty.MarshallingCodeCFactory;
import com.fnii.netty.protocol.SmartCarDecoder;
import com.fnii.netty.protocol.SmartCarEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: tangxiaoshuang
 * @date: 2018/9/15 10:13
 * @desc:
 */
@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private ChannelInboundHandlerAdapter serverHandler;

    /**
     * 每次新建channel时（即每有一个客户端连接时）都会创建属于该channel自己的pipeline
     * 相当于都会创建相应的ChannelHandler对象实例，除非addLast时传入的是相同的ChannelHandler
     * 那这个ChannelHandler必须有@Shareable修饰
     * 因为Decoder是有状态的，即它是非安全的，Decoder是不可以被@Shareable标注的
     * @param ch
     */
    @Override
    protected void initChannel(SocketChannel ch) {

        /***
         * 所有的ChannelHandler 不是inBound就是outBount
         */

        /**
         * Marshalling编码
         */
    //    ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
////        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
        ChannelPipeline pipeline = ch.pipeline();
        /*
        LineBasedFrameDecoder依次遍历ByteBuf中的可读字节，判断是否有"\n"或"\r\n",如果有就以此为结束。它是以换行符为结束标志的解码器
        直到遇到"\n"或"\r\n" 才算一条消息，否则netty不会接受它
        netty 自己封装的拆包粘包      不足1024会自动补空格
        */
        //    pipeline.addLast(new LineBasedFrameDecoder(1024));

        /**
         * 自定义分隔符
         */
//        ByteBuf buf = Unpooled.copiedBuffer("}".getBytes());
//        pipeline.addLast(new DelimiterBasedFrameDecoder(1024, buf));

        //    pipeline.addLast(new StringDecoder());
        //   pipeline.addLast(new StringEncoder());

        //自定义协议
        pipeline.addLast("encoder", new SmartCarEncoder());
        pipeline.addLast("decoder", new SmartCarDecoder());

        pipeline.addLast("handler", serverHandler);
    }
}
