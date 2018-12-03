package javase.customAnnotation;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: tangxiaoshuang
 * @date: 2018/8/22 09:31
 * @desc: 日志切面
 */
@Aspect
@Component("argsAspect")
public class ArgsAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static Gson gson = new Gson();

	@Pointcut("execution(* javase.*.*.*(..))")
	public void argsAspect() {

	}

//	@Pointcut("@annotation(javase.customAnnotation.Test)")
//	public void argsAspect() {
//
//	}

//	@Around("argsAspect()")
	public Object around(ProceedingJoinPoint joinPoint) {
//		Object[] args = joinPoint.getArgs();
//		for (Object obj : args) {
//			System.out.println(gson.toJson(obj));
//		}
		Object target = joinPoint.getTarget();
		Signature sig = joinPoint.getSignature();
		MethodSignature ms = (MethodSignature)sig;
		Method m = ms.getMethod();
		logger.info("Enter {}.{}()", target.getClass().getName(), m.getName());
		Object proceed = null;
		try {
			proceed = joinPoint.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		logger.info("Exit {}()", sig.getName());
		return proceed;
	}

//	@Before("argsAspect()")
	public void before() {
		System.out.println("before");
	}
}
