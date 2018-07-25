package spring.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.entity.Permission;
import spring.mvc.dao.PermissionDao;
import spring.mvc.service.PermissionService;

import java.util.List;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/24 18:08
 * @desc:
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;
    @Override
    public List<Permission> findPermissionByUserId(int userId) {
        return permissionDao.findPermissionByUserId(userId);
    }
}
