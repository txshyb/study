package zookeeper.rmi;

import zookeeper.TestService;
import zookeeper.TestServiceImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author: tangxiaoshuang
 * @date: 2018/11/22 10:03
 * @desc:
 */
public class Server {

    public static void main(String[] args) {
        TestService testService = new TestServiceImpl();
        String name = testService.getClass().getName();
        String rmi = "rmi://127.0.0.1:11125/rmi.TestServiceImpl";
        try {
            LocateRegistry.createRegistry(11125);
            Naming.rebind(rmi,new TestServiceImpl());

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
