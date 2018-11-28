package zookeeper;

import java.io.Serializable;

/**
 * @author: tangxiaoshuang
 * @date: 2018/11/22 10:05
 * @desc:
 */
public class TestServiceImpl implements TestService,Serializable{
    @Override
    public void sayHello(String name) {
        System.out.println("hello " + name);
    }
}
