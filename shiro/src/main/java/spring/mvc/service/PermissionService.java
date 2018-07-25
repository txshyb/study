package spring.mvc.service;

import spring.entity.Permission;

import java.util.List;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/24 18:01
 * @desc:
 */
public interface PermissionService {
    List<Permission> findPermissionByUserId(int userId);
}
