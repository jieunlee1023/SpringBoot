package com.tencoding.blog.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import lombok.extern.slf4j.Slf4j;



@Slf4j
//3. Global이 아닌 해당 패턴을 매칭하고 싶을 때
//@Component
@WebFilter(urlPatterns = "/api/user/*") 
// 여기서 설정 후 메인 함수에 추가해주어야 한다.
public class GlobalFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1.
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		// 2.
		ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper(
				(HttpServletRequest) request);

		ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper(
				(HttpServletResponse) response);

//		BufferedReader br = httpServletRequest.getReader();
//		br.lines().forEach(line->{
//			log.info(">>>>>url {}, line:{}", url, line);
//		});
		
		//★ 컨텐트를 가지고 오려면 doFilter를 위에다가 사용
		chain.doFilter(httpServletRequest, httpServletResponse);

		//Req
		String url = httpServletRequest.getRequestURI();
		String reqContent = new String(httpServletRequest.getContentAsByteArray());
		log.info(">>>req uri {}", url);
		log.info(">>>reqContent {}", reqContent);

		
		//Res
		int httpStatusCode = httpServletResponse.getStatus();
		String resContent = new String(httpServletResponse.getContentAsByteArray());
		log.info(">>>res httpStatusCode {}", httpStatusCode);
		log.info(">>>resContent {}", resContent);
		
		// I/O 발생시 문자열을 다 읽어 내었기 때문에 마지막에 내려 줄 내용이 없다. --> No Content가 응답되었다.
		// 해결 방법은
		httpServletResponse.copyBodyToResponse();
	}

}
