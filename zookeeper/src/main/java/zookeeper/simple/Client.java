package zookeeper.simple;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import zookeeper.TestService;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: tangxiaoshuang
 * @date: 2018/11/22 10:54
 * @desc:
 */
public class Client {

    public static void main(String[] args) throws Exception {
        List<String> urls = new ArrayList<>();
        ZooKeeper zooKeeper = new ZooKeeper("172.171.51.151:2181", 2000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });
        getUrls(urls, zooKeeper);

        while (true) {
            try {
                //负载均衡策略调用服务端（此处为随机策略）
                int i = new Random().nextInt(urls.size());
                System.out.println(urls.get(i));
                TestService testService = (TestService) Naming.lookup(urls.get(i));
                testService.sayHello("历史");
                System.out.println();
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private static void getUrls(final List<String> urls, ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/txs", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //当node子节点改变时触发
                //但是zookeeper通信触发机制是有时间的，当还未触发前，urls里的值还是未变的
                if (event.getType() == Event.EventType.NodeChildrenChanged) {
                    try {
                        //重新给urls赋值
                        getUrls(urls, zooKeeper);
                        System.out.println("更新");
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        urls.clear();
        for (String str : children) {
            byte[] data = zooKeeper.getData("/txs/" + str, null, null);
            urls.add(new String(data));
        }
    }
}
