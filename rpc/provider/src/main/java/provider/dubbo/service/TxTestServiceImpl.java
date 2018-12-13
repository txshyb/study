package provider.dubbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import provider.dubbo.dao.TxTestMapper;
import provider.dubbo.model.User;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/11 20:54
 * @desc:
 */
@Transactional
@com.alibaba.dubbo.config.annotation.Service
public class TxTestServiceImpl implements TxTestService {

    @Autowired
    TxTestMapper txTestMapper;

    @Override
    public void addUser() {
        User user = new User("小hng","12311");
        txTestMapper.insertUser(user);
    }
}
