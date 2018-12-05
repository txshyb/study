package javase.concurrent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/5 10:37
 * @desc:
 */
@Controller
public class AtomicTestController {

    private AtomicInteger atomiccount = new AtomicInteger(0);
    private int count = 0;

    /** 压测即可测出最终的结果  AtomicInteger 因为getAndIncrement的原子性所以最终的值就是5000，而int的值不一定 因为i++  不是原子操作
     *  ab -n 5000 -c 300 "http://localhost:19092/atomicTest"
     * @return
     */
    @RequestMapping("/atomicTest")
    @ResponseBody
    public String test() {
        atomiccount.getAndIncrement();
        count++;
        //      System.err.println("atomiccount : " + atomiccount.get() + ", count : " + count);
        return "success";
    }


    /**
     * 获取count的值查看最终结果
     * @return
     */
    @RequestMapping("/getCount")
    @ResponseBody
    public String getCount() {


        return "atomiccount : " + atomiccount.get() + ", count : " + count;
    }
}
