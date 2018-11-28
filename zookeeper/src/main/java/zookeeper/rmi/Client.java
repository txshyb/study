package zookeeper.rmi;

import zookeeper.TestService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author: tangxiaoshuang
 * @date: 2018/11/22 10:03
 * @desc:
 */
public class Client {

    public static void main(String[] args) {
        try {
           TestService testService = (TestService)Naming.lookup("rmi://127.0.0.1:11125/rmi.TestServiceImpl");
           testService.sayHello("历史");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
