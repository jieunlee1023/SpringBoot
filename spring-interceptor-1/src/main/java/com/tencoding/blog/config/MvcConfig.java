package com.tencoding.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.tencoding.blog.interceptor.AuthUserInterceptor;
import lombok.RequiredArgsConstructor;


@Configuration // class와 멤버변수 모두 객체생성
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer{

	//@Autowired // AuthUserInterceptor를 가져와서 쓴다
			   // 조심해서 써야한다! 순환 참조..!
	private final AuthUserInterceptor authUserInterceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(authUserInterceptor);
		
//		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
