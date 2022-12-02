package com.example.springdi;

import java.util.Base64;

public class MyBase64Encoder implements IEncoder {

	@Override
	public String encode(String msg) {

		//인코드 -> 숫자를 문자로
		//디코드 -> 문자를 숫자로
		
		return Base64.getEncoder().encodeToString(msg.getBytes());
	}
	
	//테스트 코드
	public static void main(String[] args) {
		MyBase64Encoder base64Encoder = new MyBase64Encoder();
		String msg = base64Encoder.encode("안녕하세요 ~ ! ");
		System.out.println(msg);
	}
	

}
