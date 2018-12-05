package com.fnii.netty.protocol;

import java.util.Arrays;

/**
 * @author: tangxiaoshuang
 * @date: 2018/9/15 10:08
 * @desc:
 */
public class SmartCarProtocol {

    private int headData = ConstantValue.HEAD_DATA;

    private int contentLength;

    private byte[] content;

    public SmartCarProtocol(int contentLength, byte[] content) {
        this.contentLength = contentLength;
        this.content = content;
    }

    public int getHeadData() {
        return headData;
    }

    public void setHeadData(int headData) {
        this.headData = headData;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

}
