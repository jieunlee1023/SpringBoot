package com.example.springdi;

//전략패턴
public class MyEncoder {
	
	private IEncoder iEncoder;
	
	// DI
	// 의존 주입 받는다.
	public MyEncoder(IEncoder iEncoder) {
		this.iEncoder = iEncoder;
	}
	
	// 기능 encoding
	public String encode(String msg) {
		return iEncoder.encode(msg);
	}
	
}
