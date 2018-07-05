package javase.inner;

/**
 * @author tangxiaoshuang
 * @date 2018/6/12 16:56
 */
class Student {
    private int rollNo;
    private Name name;

    public Student(int rollNo) {
        this.rollNo = rollNo;
    }

    public Student() {
    }

    public int getRollNo() {
        return rollNo;
    }
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
    public Name getName() {
        return name;
    }
    public void setName(Name name) {
        this.name = name;
    }
    class Name {
        public String firstName;
        public String lastName;
    }
}
