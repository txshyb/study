package javase.concurrentPattern.WorkerThreadPattern.workerThreadPattern2;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/8/1 15:16
 * @desc:
 */
public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel(5);   // 工人线程的數量
        channel.startWorkers();
        new ClientThread("Alice", channel).start();
  //      new ClientThread("Bobby", channel).start();
   //     new ClientThread("Chris", channel).start();
    }
}
