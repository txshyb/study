package gson.inner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tangxiaoshuang
 * @date 2018/6/12 16:54
 */
public class InnerClassTest2 {

    public static void main(String[] args) {
        Gson gson = new Gson();

        Student student1 = new Student(1);
        Student student2 = new Student(2);
        Teacher teacher = new Teacher("t");

        Map<String,List<Student>> map = new HashMap<>();
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        map.put("学生",list);

        Teacher.Students students = new Teacher.Students(map);
        teacher.setStudents(students);

        String json = gson.toJson(teacher);
        System.out.println(json);

        Teacher t = gson.fromJson(json,Teacher.class);
        System.out.println(t);  //debug查看对象全部转过来了
    }



}
