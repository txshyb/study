package designPatterns.future;

/**
 * @author tangxiaoshuang
 * @date 2018/5/26 9:26
 */
public class FutureData implements Data {

    private Data realData;
    private boolean hasData = false;

    public synchronized void setRealData(Data realData) {
        if(hasData) {
            return;
        }
        this.realData = realData;
        notify();
    }

    @Override
    public synchronized String getResponse() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return realData.getResponse();
    }
}
