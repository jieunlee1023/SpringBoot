package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//전략패턴
@Component
public class MyEncoder {
	
	private IEncoder iEncoder;
	
	// DI
	// 의존 주입 받는다.
	public MyEncoder(@Qualifier("MyUrlEncoder") IEncoder iEncoder) {
		this.iEncoder = iEncoder;
	}
	
	public void setIEncoder(IEncoder iEncoder) {
		this.iEncoder = iEncoder;
	}
	
	// 기능 encoding
	public String encode(String msg) {
		return iEncoder.encode(msg);
	} 
	
}
