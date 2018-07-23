package spring.cache.entity;

/**
 * @author tangxiaoshuang
 * @date 2018/7/5 16:13
 * @desc
 */
public class Param {
    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Param{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
