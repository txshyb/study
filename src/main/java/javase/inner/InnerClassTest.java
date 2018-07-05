package javase.inner;

import com.google.gson.Gson;

/**
 * @author tangxiaoshuang
 * @date 2018/6/12 16:49
 *
 * 内部类json解析
 */
public class InnerClassTest {
    public static void main(String args[]) {
        Student student = new Student();
        student.setRollNo(1);
        Student.Name name = student.new Name();

        name.firstName = "Mahesh";
        name.lastName = "Kumar";
        student.setName(name);
        Gson gson = new Gson();

        String jsonString = gson.toJson(student);
        System.out.println(jsonString);
        student = gson.fromJson(jsonString, Student.class);

        System.out.println("Roll No: "+ student.getRollNo());
        System.out.println("First Name: "+ student.getName().firstName);
        System.out.println("Last Name: "+ student.getName().lastName);

        String nameString = gson.toJson(name);
        System.out.println(nameString);

        name = gson.fromJson(nameString,Student.Name.class);
        System.out.println(name.getClass());
        System.out.println("First Name: "+ name.firstName);
        System.out.println("Last Name: "+ name.lastName);
    }

}
