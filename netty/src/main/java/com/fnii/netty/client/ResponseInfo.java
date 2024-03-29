package com.fnii.netty.client;

import java.io.Serializable;

/**
 * @author tangxiaoshuang
 * @date 2018/5/30 11:05
 */
public class ResponseInfo implements Serializable {

    private Code code;

    public ResponseInfo(Code code) {
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "code=" + code +
                '}';
    }
}
