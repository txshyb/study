package javase.jvm;

/**
 * @author tangxiaoshuang
 * @date 2018/6/26 8:59
 */
public class Test2 {
    public static void main(String[] args) {


        byte[] b ;
        for (int i=0;i<4;i++) {
            b = new byte[1024*1024];
        }
      //  System.gc();
    }
}
