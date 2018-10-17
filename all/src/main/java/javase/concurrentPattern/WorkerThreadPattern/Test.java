package javase.concurrentPattern.WorkerThreadPattern;

import javase.concurrentPattern.WorkerThreadPattern.workerThreadPattern2.Channel;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/8/1 13:53
 * @desc:
 */
public class Test {
    public static void main(String[] args) {
        ThreadPoolChannel threadPoolChannel = new ThreadPoolChannel(5);
        threadPoolChannel.startThread();
        new ClientThread("a",threadPoolChannel).start();

    }
}
