package javase.concurrentPattern.WorkerThreadPattern;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/8/1 11:18
 * @desc:
 */
public class WorkerThread extends Thread {
    private final ThreadPoolChannel threadPoolChannel;

    public WorkerThread(String name,ThreadPoolChannel threadPoolChannel) {
        super(name);
        this.threadPoolChannel = threadPoolChannel;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Work work = threadPoolChannel.takeWork();
                work.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
