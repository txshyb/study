package com.fnii.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author: tangxiaoshuang
 * @date: 2018/9/15 10:08
 * @desc:
 */
public class SmartCarEncoder extends MessageToByteEncoder<SmartCarProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, SmartCarProtocol msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getHeadData());
        out.writeInt(msg.getContentLength());
        out.writeBytes(msg.getContent());
    }
}
