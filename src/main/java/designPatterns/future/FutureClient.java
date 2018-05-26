package designPatterns.future;

/**
 * @author tangxiaoshuang
 * @date 2018/5/26 9:26
 */
public class FutureClient {

    public Data request() {
        final FutureData futureData = new FutureData();

        new Thread(new Runnable() {

            @Override
            public void run() {
                Data data = new RealData();
                futureData.setRealData(data);
            }
        }).start();
        return  futureData;
    }
}
