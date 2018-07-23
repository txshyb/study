package netty.test2;

import java.io.Serializable;

/**
 * @author tangxiaoshuang
 * @date 2018/5/30 11:04
 */
public class RequestInfo implements Serializable {
    private String head;
    private String context;

    public RequestInfo() {
    }

    public RequestInfo(String head, String context) {
        this.head = head;
        this.context = context;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                "head='" + head + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}
