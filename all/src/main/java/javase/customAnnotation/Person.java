package javase.customAnnotation;

/**
 * @author tangxiaoshuang
 * @date 2018/5/29 14:11
 */
public class Person {

    private String name;
    @MyDataFormat
    private String brith;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrith() {
        return brith;
    }

    public void setBrith(String brith) {
        this.brith = brith;
    }
}
