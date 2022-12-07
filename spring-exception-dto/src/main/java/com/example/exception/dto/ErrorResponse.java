package com.example.exception.dto;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {

	private int statusCode; 
	private String requestUrl; 
	private int code; // 1이면 성공, -1이면 실패
	private String message;
	private String resultCode; //Fail, Success
	private List<MyError> errorList;
}
