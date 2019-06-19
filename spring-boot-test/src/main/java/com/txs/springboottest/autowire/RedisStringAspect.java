package com.txs.springboottest.autowire;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: tangxiaoshuang
 * @date: 2019/6/19 16:39
 * @desc:
 */
@Aspect
@Component
public class RedisStringAspect {

    @Pointcut("@annotation(RedisStringAnn)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if ((Integer)args[0] == 1) {
            ThreadLocalCache.threadLocal.set(new RedisStringImpl1());
        } else {
            ThreadLocalCache.threadLocal.set(new RedisStringImpl2());
        }
    }
}
