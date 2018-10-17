package javase.concurrentPattern.WorkerThreadPattern.workerThreadPattern2;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/8/1 15:16
 * @desc:
 */
public class WorkerThread extends Thread {
    private final Channel channel;
    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }
    public void run() {
        try {
            while (true) {
                Request request = channel.takeRequest();
                request.execute();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}