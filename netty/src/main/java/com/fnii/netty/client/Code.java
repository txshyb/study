package com.fnii.netty.client;

/**
 * @author tangxiaoshuang
 * @date 2018/5/30 11:08
 */
public enum Code {

    //拒绝访问
    reject(4000, "拒绝访问"),
    /**
     *     ok
     */
    apply(3000, "OK");
    private Integer num;
    private String msg;

    Code(Integer num, String msg) {
        this.num = num;
        this.msg = msg;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
