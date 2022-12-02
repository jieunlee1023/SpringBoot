package com.example.springdi;

public class MyAsonEncoder implements IEncoder {

	@Override
	public String encode(String msg) {
		String result = "AAA " + msg + "AAAA";
		return result;
	}
	

}
