package com.txs.springboottest.动态实例化;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

import java.util.Set;

@org.springframework.stereotype.Controller
public class Controller {


    @RequestMapping("/setObject")
    @ResponseBody
    public Object setObject() {
        BeanFactory beanFactory = BeanFactoryHolder.getBeanFactory();
        BeanDefinitionBuilder beanMer = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)beanFactory;
        //动态注册类
        defaultListableBeanFactory.registerSingleton("user", new User(10,"xiao"));
        defaultListableBeanFactory.registerSingleton("user2", new User(12,"花收到就好"));
//        defaultListableBeanFactory.registerBeanDefinition("user", beanMer.getRawBeanDefinition());
        return 1;
    }

    @RequestMapping("/getObject")
    @ResponseBody
    public Object getObject() {
        //动态获取类
        User user1 = (User)ApplicationHolder.getApplication().getBean("user");
        User user2= (User)ApplicationHolder.getApplication().getBean("user2");
        User user3 = (User)ApplicationHolder.getApplication().getBean(User.class); // error    expected single matching bean but found 2: user,user2
        System.out.printf(user2.toString());
        System.out.printf(user3.toString());
        return 2;
    }

    @RequestMapping("/setRedisConfig")
    @ResponseBody
    public void setRedisConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(2);
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxWaitMillis(3000);
        jedisPoolConfig.setMinIdle(1);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        jedisConnectionFactory.setHostName("127.0.0.1");
        jedisConnectionFactory.setPort(6379);
//        jedisConnectionFactory.setPassword("future1234");
        jedisConnectionFactory.setDatabase(8);
        jedisConnectionFactory.setUsePool(true);
        //TODO  与分布式有关 待查  不加上报错
        JedisShardInfo jedisShardInfo = new JedisShardInfo("127.0.0.1",6379);

        jedisConnectionFactory.setShardInfo(jedisShardInfo);

        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(jedisConnectionFactory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        template.setValueSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();

        BeanFactory beanFactory = BeanFactoryHolder.getBeanFactory();
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)beanFactory;
        defaultListableBeanFactory.registerSingleton("testRedis", template);
    }

    @RequestMapping("/getRedisConfig")
    @ResponseBody
    public Object getRedisConfig() {
        //动态获取类
        RedisTemplate redisTemplate = (RedisTemplate)ApplicationHolder.getApplication().getBean("testRedis");

        Set keys = redisTemplate.keys("*");
        return keys;
    }
}
