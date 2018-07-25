package spring.mvc.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/24 18:15
 * @desc:
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @RequestMapping("/test")
    @RequiresPermissions("user:test")
    public String test() {
        return "index";
    }
    
    @RequestMapping("/test2")
    @RequiresRoles("user")
    public String test2() {
        return "index";
    }
}
