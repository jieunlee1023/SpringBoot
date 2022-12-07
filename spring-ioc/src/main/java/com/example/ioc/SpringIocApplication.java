package com.example.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringIocApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIocApplication.class, args);
		
		// 개발자가 직접생성 : MyBase64Encoder base64Encoder = new MyBase64Encoder();
		
		ApplicationContext context = ApplicationContextProvider.getContext();
		
		String myUrl = "www.naver.com/?q=우리 이길까 ? ? ?";
		
		MyBase64Encoder base64Encoder = context.getBean(MyBase64Encoder.class);
//		MyEncoder encoder1 = new MyEncoder(base64Encoder);
//		String result1= encoder1.encode(myUrl);
//		System.out.println("base64Encoder : " + result1);
		
		UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
//		MyEncoder encoder2 = new MyEncoder(urlEncoder);
//		String result2 = encoder2.encode(myUrl);
//		System.out.println("urlEncoder : " + result2);
		
		MyEncoder encoder = context.getBean(MyEncoder.class);
		
		
		// 변경처리
		encoder.setIEncoder(base64Encoder);
		String result = encoder.encode(myUrl);
		System.out.println(result);
	}

}
