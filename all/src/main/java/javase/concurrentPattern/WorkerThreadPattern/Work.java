package javase.concurrentPattern.WorkerThreadPattern;

import java.util.Random;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/8/1 11:26
 * @desc:
 */
public class Work {

    private final String name; //  委托者
    private final int number;  // 请求编号
    private static final Random random = new Random();
    public Work(String name,int number) {
        this.name = name;
        this.number=number;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executes " + this);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public String toString() {
        return "[ Work from " + name + " No." + number + " ]";
    }
}
