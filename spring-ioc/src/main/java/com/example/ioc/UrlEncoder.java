package com.example.ioc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

@Component("MyUrlEncoder")
public class UrlEncoder implements IEncoder{

	@Override
	public String encode(String msg) {
		String result = null;
		try {
			result  = URLEncoder.encode(msg, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//테스트 코드
	public static void main(String[] args) {
		// 우리가 만듦
		UrlEncoder urlEncoder = new UrlEncoder();
		String msg = urlEncoder.encode("안녕하세요 반가워요!");
		System.out.println(msg);
	}

}
