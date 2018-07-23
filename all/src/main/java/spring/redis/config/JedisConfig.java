package spring.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author tangxiaoshuang
 * @date 2018/7/16 10:15
 * @desc
 */
@Configuration
public class JedisConfig {
//
//    @Bean
//    public JedisPool pool() {
//        JedisPoolConfig config = new JedisPoolConfig();
//        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
//        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
//        config.setMaxTotal(1);
//        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
//        config.setMaxIdle(1);
//        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；单位毫秒
//        //小于零:阻塞不确定的时间,  默认-1
//        config.setMaxWaitMillis(100);
//        //在borrow(引入)一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
//        config.setTestOnBorrow(true);
//        //return 一个jedis实例给pool时，是否检查连接可用性（ping()）
//        config.setTestOnReturn(true);
//        //connectionTimeout 连接超时（默认2000ms）
//        //soTimeout 响应超时（默认2000ms）
//        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379,  200);
//        return pool;
//    }
//
//    @Bean
//    public Jedis getResource(JedisPool jedisPool) {
//        return jedisPool.getResource();
//    }

    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1",6379);
        while(true) {
            jedis.publish("test", "hdhd");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
