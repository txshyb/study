package spring.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.cache.entity.Param;
import spring.cache.service.CacheService;

/**
 * @author tangxiaoshuang
 * @date 2018/7/5 15:45
 * @desc
 */
@Controller("cache")
@RequestMapping("/cache")
public class TestController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping("/test1")
    public Object test1(Integer i) {
        return cacheService.getKey(i);
    }

    @RequestMapping("/test2")
    public Object test2(Param param) {
        return cacheService.getKey2(param);
    }

    @RequestMapping("/cancle")
    public void cancle(Integer key) {
        cacheService.cancle(key);
    }
}
