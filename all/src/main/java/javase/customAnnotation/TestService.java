package javase.customAnnotation;

import org.springframework.stereotype.Service;

/**
 * @author: tangxiaoshuang
 * @date: 2018/11/1 18:01
 * @desc:
 */
@Service
public class TestService {
    public void test(){
        System.out.println("service");
    }
}
