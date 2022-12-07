package com.example.demo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그 찍는 어노테이션
@Aspect
@Component
public class ParameterAop {
	
	//controller 패키지 하위에 있는 모든 메소드를 찾아 보겠다 (관심을 갖겠다)
	@Pointcut (value = "execution(* com.example.demo.controller..*.*(..))")
	public void myPointCut() {
	}
	
	// 메소드가 실행하기 이전
	@Before ("myPointCut()")
	public void myBefore(JoinPoint joinPoint) {
		// aop를 활용해서 어떤 클래스에 어떤 메서드가 동작했는지 알아 볼 수 있다.
		// 1. 메소드 이름을 알아보자
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		System.out.println("리턴 타입, 실행 된 풀 패키지 메소드 명 : " + methodSignature);
		
		Method method = methodSignature.getMethod();
		
		System.out.println("----------------------------------");
		log.info("method - {}", method);
		Object[] args = joinPoint.getArgs();
		for (Object object : args) {
			System.out.println("type : "+object.getClass().getSimpleName());
			System.out.println("value : "+object);
		}
		System.out.println("----------------------------------");
	}
	
	
	@AfterReturning(value = "myPointCut()", returning = "returnObj")
	public void myAfterReturn(JoinPoint joinPoint, Object returnObj) { 
		//매개변수로 들어오는 Object의 변수명은 returning 파라미터이름과 같아야 한다.
		System.out.println("Return Value : " + returnObj);
		
	}
}
