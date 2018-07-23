package javase.designpattern.singleton;

/**
 * @author tangxiaoshuang
 * @date 2018/7/3 9:27
 *
 * 完美的单例
 */
public class Test1 {

    private Test1() {

    }

    private static class InstanceHold {
        private static Test1 instance = new Test1();
    }



    public static void main(String[] args) {
        for (int i=0;i<100;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Test1.InstanceHold.instance);
                }
            }).start();
        }
    }
}
