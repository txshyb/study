package consumer.dubbo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import consumer.dubbo.dao.TxTestMapper;
import consumer.dubbo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/11 20:54
 * @desc:
 */
@Transactional
@Service
public class TxTestServiceImpl implements TxTestService {

    @Autowired
    TxTestMapper txTestMapper;

    //dubbo服务
    @Reference
    provider.dubbo.service.TxTestService txTestService;

    @Override
    public void addUser(User user) {
        txTestMapper.insertUser(user);

        txTestService.addUser();
        int i = 1/0;
    }
}
