package com.tencoding.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.tencoding.blog.annotation.AuthUser;

// 인터셉터를 우리가 직접 구현해 보자 - 추가하기 !
@Component
public class AuthUserInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		
		String uriStr = request.getRequestURI();
		String queryString = request.getQueryString(); // ?tecoId=teco <--
		System.out.println(queryString);
		
		// 체크 어노테이션 여부 확인!
		boolean validAuthUser = checkAnnotation(handler, AuthUser.class);
		System.out.println("체~~~~크 : "+validAuthUser);
		
		if (validAuthUser) {
			
			if (queryString == null) {
				return false;
			}
			//한단계 더 확인
			if (queryString.equals("tecoId=teco")) {
				return true;
			} else {
				return false;
			}
		} 
 
		return true;
	}

	//authUser 어노테이션이 있나요?
	private boolean checkAnnotation(Object handler, Class clazz) {

		// 리소스를 요청시 무조건 허용
		if (handler instanceof ResourceHttpRequestHandler) {
			System.out.println("리소스 요청 class : " + clazz.getName());
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		if (null != handlerMethod.getMethodAnnotation(clazz)
				|| null != handlerMethod.getBeanType().getAnnotation(clazz)) {
			System.out.println("어노테이션 체크 : " + clazz.getName());
		
			
			return true;
		}
		return false;
	}

}
