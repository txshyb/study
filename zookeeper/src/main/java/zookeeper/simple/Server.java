package zookeeper.simple;

import org.apache.zookeeper.*;
import zookeeper.TestService;
import zookeeper.TestServiceImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;

/**
 * @author: tangxiaoshuang
 * @date: 2018/11/22 10:03
 * @desc:
 */
public class Server {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        int port = 11130;
        String host = "127.0.0.1";
        TestService testService = new TestServiceImpl();
        String name = testService.getClass().getName();
        String rmi = "rmi://" + host + ":" + port + "/" + name;
        ZooKeeper zooKeeper = null;
        zooKeeper = new ZooKeeper("172.171.51.151:2181", 20000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //zookeeper 连接成功
                if(event.getState() == Event.KeeperState.SyncConnected) {
                    //countDownLatch 减一  使其变为0 唤醒线程
                    countDownLatch.countDown();
                }
            }
        });
        //线程等待，直到countDownLatch的计数器变为0才唤醒   这时 zookeeper是连接好的    防止zookeeper还未连接成功时，程序已经走到下面了（Future模式）
        countDownLatch.await();

        // 先创建一个我用的node
     //   zooKeeper.create("/txs", rmi.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //把发布的服务地址记录在zookeeper （CreateMode.EPHEMERAL_SEQUENTIAL    EPHEMERAL：客户端断开后node删除；SEQUENTIAL：node名相同也会新建 会在名称后追加一串id）
        //dubbo提供者底层原理
        zooKeeper.create("/txs/test", rmi.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        //使用RMI发布服务
        LocateRegistry.createRegistry(port);
        Naming.rebind(rmi, new TestServiceImpl());
        Thread.sleep(Long.MAX_VALUE);
    }
}
