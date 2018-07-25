package spring.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import spring.entity.Permission;
import spring.entity.User;
import spring.mvc.service.PermissionService;
import spring.mvc.service.UserService;

import java.util.List;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/23 18:29
 * @desc:
 */
@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    /**
     * 获取权限
     * @param principalCollection
     * @return
     * 调用需要权限的方法时会先执行该方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User)principalCollection.getPrimaryPrincipal();
        List<Permission> permissions = permissionService.findPermissionByUserId(user.getId());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (Permission permission : permissions) {
            if(StringUtils.isEmpty(permission.getCode())){
                continue;
            }
            authorizationInfo.addStringPermission(permission.getCode());
        }
        return authorizationInfo;
    }

    /**
     * 获取认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     * subject.login()时会执行该方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        User user = userService.findUserByName(username);
        if(null == user) {
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        return simpleAuthenticationInfo;
    }
}
