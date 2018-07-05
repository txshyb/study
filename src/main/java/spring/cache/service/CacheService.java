package spring.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring.cache.entity.Param;

/**
 * @author tangxiaoshuang
 * @date 2018/7/5 15:40
 * @desc
 */
@Component
public class CacheService {

    private Logger logger = LoggerFactory.getLogger(CacheService.class);

    //

    /**
     * i值相同就不进入缓存，不相同则进入   对基本数据类型管用
     * 也可写成@Cacheable(value = "myCache",key = "#i")
     * @param i
     * @return
     */
    @Cacheable(value = "myCache")
    public String getKey(int i) {
        logger.info("key|  {}",i);
        return "key : " + i;
    }


    /**
     * key = "#param.age"
     *
     * param.age值相同就不进入缓存，不相同则进入   不可以对一个对象使用
     *
     * @param param
     * @return
     */
    @Cacheable(value = "myCache",key = "#param.age")
    public String getKey2(Param param) {
        logger.info("key|  {}",param);
        return param.toString();
    }

    /**
     * 从myCache缓存中移除了key为i的数据
     * key = "#param.age"   也可以和上面一样传入的是对象，key设为对象中的一个值
     * @param i
     */
    @CacheEvict(value = "myCache")
    public void cancle(Integer i) {
        logger.info("cancle|{}",i);
    }
}
