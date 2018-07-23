package javase.fanxing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author tangxiaoshuang
 * @date 2018/6/6 9:31
 */
public class Test {
    public static void main(String[] args) {
        new Test().test();
    }

    public  void test() {
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("Jack", 20));
        list.add(new Person("Cv", 21));
        list.add(new Person("WWW", 22));


        List<Student> list2 = new ArrayList<Student>();
        list2.add(new Student("qqq", 22, 1));
        list2.add(new Student("eee", 23, 2));
        list2.add(new Student("qwer", 20, 3));
        System.out.println("extends");
        print(list);
        print(list2);
        System.out.println();
        System.out.println("super");
        print2(list);
        print2(list2);

    }

    //上限 泛型
    public void print(List<? extends Person> list) {
        Iterator<? extends Person> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    //下限 泛型
    public void print2(List<? super Student> list) {
        Iterator<? super Student> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    class Person {
        String name;
        int age;

        public Person(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + "]";
        }

    }

    class Student extends Person {
        private int no;

        public Student(String name, int age, int no) {
            super(name, age);
            this.no = no;
        }

        @Override
        public String toString() {
            return "Student [no=" + no + ", name=" + name + ", age=" + age + "]";
        }

    }
}
