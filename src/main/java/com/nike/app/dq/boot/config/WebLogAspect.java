
package com.nike.app.dq.boot.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.nike.app.dq.boot.util.log.SimpleLogger;

@Aspect
@Component
public class WebLogAspect {

	ThreadLocal<Long> startTime = new ThreadLocal<>();

	@Pointcut("execution(public * com.nike.app.dq.boot.web.mvc.controller..*.*(..))")
	public void webLog() {

	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		SimpleLogger.log(SimpleLogger.DEBUG, getClass(), "before CONTROLLER_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut="webLog()", returning="ret")
	public void doAfterReturning(Object ret) throws Throwable {
		SimpleLogger.log(SimpleLogger.DEBUG, getClass(), "after CONTROLLER_METHOD : SPEND TIME = " + (System.currentTimeMillis() - startTime.get()));
	}
}