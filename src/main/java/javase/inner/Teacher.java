package javase.inner;

import java.util.List;
import java.util.Map;

/**
 * @author tangxiaoshuang
 * @date 2018/6/12 16:54
 */
public class Teacher {
    private String name;
    private Students students;


    public Teacher(String name) {
        this.name = name;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Students {
        private Map<String,List<Student>> studemts;

        public Students(Map<String, List<Student>> studemts) {
            this.studemts = studemts;
        }

        public Map<String, List<Student>> getStudemts() {
            return studemts;
        }

        public void setStudemts(Map<String, List<Student>> studemts) {
            this.studemts = studemts;
        }
    }
}
