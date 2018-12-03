package javase.concurrent;

import org.springframework.stereotype.Service;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/3 14:42
 * @desc:
 */
@Service("conServiceImpl2")
public class ConServiceImpl2 implements ConService {

    @Override
    public String getString() {
        return "conServiceImpl2";
    }
}
