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

    /**
     * 协议开始的标准head_data，int类型，占据4个字节.
     * 表示数据的长度contentLength，int类型，占据4个字节.
     */
    public final int BASE_LENGTH = 4 + 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //可读长度必须大于基本长度
        if (in.readableBytes() > BASE_LENGTH) {
            // 防止socket字节流攻击
            // 防止，客户端传来的数据过大
            // 因为，太大的数据，是不合理的
            if(in.readableBytes() > 2048){
                in.skipBytes(in.readableBytes());
            }
            int beginReader;
            while (true) {
                // 获取包头开始的index
                beginReader = in.readerIndex();
                // 标记包头开始的index
                in.markReaderIndex();
                if (in.readInt() == ConstantValue.HEAD_DATA) {
                    break;
                }

                // 未读到包头，略过一个字节
                // 每次略过，一个字节，去读取，包头信息的开始标记
                // 略过一个字节是因为可读长度已经大于8了，前四个字节应该是协议头，上面判断为不是，则说明第一个字节肯定是垃圾数据，
                //每次读取的数据开始必然应该是以整个协议头开始的消息，因为下面in.readerIndex(beginReader)的操作
                //读索引标记为mark索引
                in.resetReaderIndex();
                //读一个字节（读索引+1）
                in.readByte();

                if (in.readableBytes() < BASE_LENGTH) {
                    return;
                }
            }
            // 消息的长度
            int length = in.readInt();
            // 判断请求数据包数据是否到齐
            if (in.readableBytes() < length) {
                // 还原读指针  把读索引恢复到协议头开始的地方
                in.readerIndex(beginReader);
                return;
            }
            // 读取data数据
            byte[] data = new byte[length];
            in.readBytes(data);

            //    SmartCarProtocol smartCarProtocol = new SmartCarProtocol(data.length,data);
            //   out.add(smartCarProtocol);
            //只返回具体内容
            out.add(new String(data,"utf-8"));
        }
    }
}
