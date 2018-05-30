package netty.test2;

/**
 * @author tangxiaoshuang
 * @date 2018/5/30 11:08
 */
public enum Code {

    reject(4000,"拒绝访问");

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
