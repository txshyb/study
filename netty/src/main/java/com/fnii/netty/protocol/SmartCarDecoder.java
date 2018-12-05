package com.fnii.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author: tangxiaoshuang
 * @date: 2018/9/15 10:08
 * @desc:
 */
public class SmartCarDecoder extends ByteToMessageDecoder {

    public final int BASE_LENGTH = 4 + 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() > BASE_LENGTH) {
            //不限制大小
//            if(in.readableBytes() > 2048){
//                in.skipBytes(in.readableBytes());
//            }
            int beginReader;
            while (true) {
                beginReader = in.readerIndex();
                in.markReaderIndex();
                if (in.readInt() == ConstantValue.HEAD_DATA) {
                    break;
                }

                in.resetReaderIndex();
                in.readByte();

                if (in.readableBytes() < BASE_LENGTH) {
                    return;
                }
            }

            int length = in.readInt();
            if (in.readableBytes() < length) {
                in.readerIndex(beginReader);
                return;
            }

            byte[] data = new byte[length];
            in.readBytes(data);

            //    SmartCarProtocol smartCarProtocol = new SmartCarProtocol(data.length,data);
            //   out.add(smartCarProtocol);
            //只返回具体内容
            out.add(new String(data,"utf-8"));
        }
    }
}
