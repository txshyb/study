package spring.beanpostprocessor.aop;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/2 12:43
 * @desc:
 */
@Service
//@Transactional
public class AopServiceImpl implements AopService {
    @Override
    public void test() {
        System.out.println("aopService.test");
    }
}
