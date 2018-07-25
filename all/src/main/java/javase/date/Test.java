package javase.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/25 11:28
 * @desc:
 */
public class Test {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyMMddHHmm");
    public static void main(String[] args) throws ParseException {
        Date date = simpleDateFormat.parse("2018-7-25 19:00:00");
     //   Date date = new Date("2018 7 25 19:00:00");
        String str = simpleDateFormat2.format(date);
        System.out.println(str);
    }
}
