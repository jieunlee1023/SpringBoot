package com.example.exception.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice //RestController에 대한 예외를 담당
//1. 특정 패키지만 지정하여 예외를 담당시킬 수 있다.
@RestControllerAdvice(basePackages = "com.example.exception.controller.ApiController")

//@ControllerAdvice // 페이지 리턴 시 예외 발생 담당
//public class GlobalControllerAdvice { //전체 일 때 클래스명
public class ApiControllerAdvice {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> exception(Exception e) {

		System.out.println("--------------------------------------------");
		System.out.println("에러이름:" + e.getClass().getName());
		System.out.println("메시지:" + e.getLocalizedMessage());
		System.out.println("--------------------------------------------");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
	}

	@ExceptionHandler(value = ConstraintViolationException.class) // 매개변수도 같아야 동작한다.
	public ResponseEntity<?> constraintViolationException(ConstraintViolationException e) {

		// 예외가 발생 했을 때, 클라이언트에게 Json 형식으로 내려보자
		Map<String, String> cusErrorMap = new HashMap<>();

		e.getConstraintViolations().forEach(e2 -> {
			Path path = e2.getPropertyPath();
			String msg = e2.getMessage();

			try {
				StringTokenizer stringTokenizer = new StringTokenizer(path.toString(), ".");
				if (stringTokenizer.hasMoreElements()) {
					// get.name --> get, name
					stringTokenizer.nextElement(); // get --> 다음으로 이동
//					System.out.println("get 다음 나오나요 ? : "+stringTokenizer.nextElement());
					cusErrorMap.put(stringTokenizer.nextElement().toString(), msg);
				}
				System.out.println(path);
				System.out.println(msg);
				System.out.println(e2.getInvalidValue());
			} catch (Exception e3) {
				// 한번더 작업 - StringTokenizer 예외 발생 시 처리 -> "."이 없는 경우
				// .이 없이 키값만 넘어오므로 담기만 하면 된다.
				cusErrorMap.put(path.toString(), msg);
				e.getLocalizedMessage();
			}

		});
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cusErrorMap);
	}

	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ResponseEntity<?> missingServletRequestParameterException(MissingServletRequestParameterException e) {
		HashMap<String, String> cusErrorMap = new HashMap<>();
		
//		System.out.println("--------MissingServletRequestParameterException 예외발생-------");
//		System.out.println(e.getLocalizedMessage());
		String field = e.getParameterName();
		cusErrorMap.put(field,field+"는 필수 입력값 입니다.");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cusErrorMap);
	}
	
	// Post 예외 잡음
	@ExceptionHandler(value = MethodArgumentNotValidException.class) // 매개변수도 같아야 동작한다.
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {

		HashMap<String, String> cusErrorMap = new HashMap<>();

		// name과 age 둘 다 잘못들어왔다면, 
		e.getBindingResult().getAllErrors().forEach(e2 -> {
			FieldError fieldError = (FieldError) e2;
			String fieldName = fieldError.getField();
			String message = fieldError.getDefaultMessage();
//			String rejectedValue = (String) fieldError.getRejectedValue();
			
			System.out.println("fieldName" +fieldName );
			System.out.println("message" +message );
//			System.out.println("rejectedValue" +rejectedValue );
			
			cusErrorMap.put(fieldName, message);
		});
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cusErrorMap);
	}
	
	
	
	
	
	


}
