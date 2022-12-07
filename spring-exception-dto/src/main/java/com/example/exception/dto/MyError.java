package com.example.exception.dto;

import lombok.Data;

@Data
public class MyError {

	//필드명
	//내용
	//거절 값 (reject).. 등이 필요
	
	private String field;
	private String message;
	private Object invalidValue;
	
}
