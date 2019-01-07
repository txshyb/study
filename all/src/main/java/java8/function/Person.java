package java8.function;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/13 11:25
 * @desc:
 */
public class Person {

    private String name;

    private Integer age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        System.out.println(name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
