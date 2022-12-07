package com.example.demo.aop;

import java.util.Iterator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AddHeartAop {

	@Pointcut (value = "execution(* com.example.demo.controller..*.*(..))")
	public void cut() {}
	
	@Pointcut(value = "@annotation(com.example.demo.anno.AddHeart)")
	public void addHeartAnno() {}
	
	@Around("cut() && addHeartAnno()")
	public void addHeartAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		System.out.println("...");
		Object result = proceedingJoinPoint.proceed();
		
		Thread.sleep(3000);
		// 요청자는 기다리고 있고, 서버에서 스레드가 돌아간다.
	
		System.out.println("♥");
		
	}
	
}
