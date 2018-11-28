package zookeeper;

import java.rmi.Remote;

/**
 * @author: tangxiaoshuang
 * @date: 2018/11/22 10:04
 * @desc:
 */
public interface TestService extends Remote {

    public void sayHello(String name);
}
