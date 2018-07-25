package spring.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.entity.User;
import spring.mvc.dao.UserDao;
import spring.mvc.service.UserService;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/24 15:35
 * @desc:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }
}
