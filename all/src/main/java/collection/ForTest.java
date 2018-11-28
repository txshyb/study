package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: tangxiaoshuang
 * @date: 2018/11/12 14:46
 * @desc:
 */
public class ForTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        for (String str : list) {
            if(str.equals("a")) {
          //      list.add("d");    循环遍历时不可以添加
                list.remove("c");   //循环遍历时不可删除已存在元素
            }
            System.out.println(str);
        }
    }
}
