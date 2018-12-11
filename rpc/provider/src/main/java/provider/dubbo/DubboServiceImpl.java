package provider.dubbo;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/8 20:43
 * @desc:
 */
@Service
public class DubboServiceImpl implements DubboService {

    @Override
    public String hello(String name) {
        String str = "你好，" + name;
        System.out.println(str);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return str;
    }
}
