package spring.cache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.RedisCollectionFactoryBean;

/**
 * @author tangxiaoshuang
 * @date 2018/7/5 15:14
 * @Desc 注解配置方式
 *
 *
 *
 * spring3.1内置5个缓存管理器实现
 *  SimpleCacheManager
 *  NOOPCacheManager
 *  ConcurrentMapCacheManager
 *  CompositeCacheManager
 *  EhcacheCacheManager
 *
 * 3.2后spring data又提供了两个
 *  RedisCacheManager
 *  GemfireCacheManager
 *
 */

@Configuration
@EnableCaching
public class CacheConfig {

    //ConcurrentMapCacheManager
//    @Bean
//    public CacheManager concurrentMapCacheManager() {
//        return new ConcurrentMapCacheManager();
//    }

    //EhcacheCacheManager
    @Bean
    public EhCacheCacheManager cacheManager(net.sf.ehcache.CacheManager cacheManager) {
        return new EhCacheCacheManager(cacheManager);
    }

    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }


    //RedisCacheManager
//    @Bean
//    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
//        return new RedisCacheManager(redisTemplate);
//    }
//
//    @Bean
//    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//        RedisTemplate r =  new RedisTemplate();
//        r.setConnectionFactory(jedisConnectionFactory);
//        return r;
//    }
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//    //    jedisConnectionFactory.setHostName();
//        //设置各种参数
//        return jedisConnectionFactory;
//    }
}
