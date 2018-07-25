package spring.mvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import spring.entity.User;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/24 15:36
 * @desc:
 */
public interface UserDao {
    User findUserByName(@Param("username") String username);
}
