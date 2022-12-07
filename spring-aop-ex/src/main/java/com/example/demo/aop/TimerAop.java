package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

	@Pointcut(value = "execution(* com.example.demo.controller..*.*(..))")
	public void cut() {}
	
	@Pointcut(value = "@annotation(com.example.demo.anno.Timer)")
	private void checkTimer() {}
	
	@Around("cut() && checkTimer()")
	public void around (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object result = proceedingJoinPoint.proceed();
		
		stopWatch.stop();
		System.out.println("Total Time : " + stopWatch.getTotalTimeSeconds());
	}
}
