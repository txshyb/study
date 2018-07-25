package spring.mvc.service;

import spring.entity.User;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/24 14:56
 * @desc:
 */
public interface UserService {

    public User findUserByName(String username);
}
