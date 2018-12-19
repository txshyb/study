package com.fnii.netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

public class ButeBufTest {
    public static void main(String[] args) {
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        ByteBuf heapBuf = Unpooled.buffer(10);
        ByteBuf directBuf = Unpooled.directBuffer();

        compositeByteBuf.addComponents(heapBuf,directBuf);

        Iterator<ByteBuf> iterable = compositeByteBuf.iterator();

    }
}
