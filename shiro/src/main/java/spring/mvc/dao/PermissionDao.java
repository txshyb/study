package spring.mvc.dao;

import org.apache.ibatis.annotations.Param;
import spring.entity.Permission;

import java.util.List;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/24 18:08
 * @desc:
 */
public interface PermissionDao {
    List<Permission> findPermissionByUserId(@Param("userId") int userId);
}
