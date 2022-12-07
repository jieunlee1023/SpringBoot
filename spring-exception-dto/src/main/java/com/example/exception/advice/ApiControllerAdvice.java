package com.example.exception.advice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.dto.ErrorResponse;
import com.example.exception.dto.MyError;
import com.example.exception.dto.User;

@RestControllerAdvice(basePackages = "com.example.exception.controller")
public class ApiControllerAdvice {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> exception(Exception e) {

		System.out.println("--------------------------------------------");
		System.out.println("에러이름:" + e.getClass().getName());
		System.out.println("메시지:" + e.getLocalizedMessage());
		System.out.println("--------------------------------------------");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
	}

	// post
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest req) {

		ArrayList<MyError> myErrors = new ArrayList<>();

		ex.getBindingResult().getAllErrors().forEach(e -> {
			FieldError fieldError = (FieldError) e; // 다운캐스팅 하면 편하게 쓸 수 있다.
			String fieldName = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			Object rejectValue = fieldError.getRejectedValue();

			MyError error = new MyError();
			error.setField(fieldName);
			error.setMessage(message);
			error.setInvalidValue(rejectValue);

			myErrors.add(error);

		});

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorList(myErrors);
		errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage("잘못된 요청입니다.");
		errorResponse.setCode(-1);
		errorResponse.setResultCode("FAIL");
		errorResponse.setRequestUrl(req.getRequestURI());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	// get
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ResponseEntity<?> missingServletRequestParameterException(
			MissingServletRequestParameterException e,
			HttpServletRequest req) {

		
		System.out.println("여기오삼 ???");
		ArrayList<MyError> myErrors = new ArrayList<>();

		String name = e.getParameterName();
		String msg = "";
		
		MyError error = new MyError();
		error.setField(name);
		System.out.println("name: " + name);
		if (name.equals("name")) {
			msg = "이름을 입력하시오! (필수 입력 값)";
			error.setMessage(msg);
		} else if (name.equals("age")){
			msg = "나이를 입력하시오! (필수 입력 값)";
			error.setMessage(msg);
		}
		System.out.println("msg: " + msg);
//		error.setInvalidValue(req);
		myErrors.add(error);
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorList(myErrors);
		errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage("잘못된 요청입니다.");
		errorResponse.setCode(-1);
		errorResponse.setResultCode("FAIL");
		errorResponse.setRequestUrl(req.getRequestURI());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	// get
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<?> constraintViolationException(ConstraintViolationException exception,
			HttpServletRequest req) {

		ArrayList<MyError> myErrors = new ArrayList<>();

		exception.getConstraintViolations().forEach(e -> {
			Path path = e.getPropertyPath();
			

			StringTokenizer stringTokenizer = new StringTokenizer(path.toString(), ".");

			if (stringTokenizer.hasMoreElements()) {
				String preName = stringTokenizer.nextToken();

				String lastName = stringTokenizer.nextToken();
				String message = e.getMessage();

				Object rejectValue = e.getInvalidValue();
//				System.out.println("InvalidValue : "+e.getInvalidValue());

				MyError error = new MyError();
				error.setField(lastName);
				error.setMessage(message);
				error.setInvalidValue(rejectValue);

				myErrors.add(error);

			}
		});
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorList(myErrors);
		errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage("잘못된 요청입니다.");
		errorResponse.setCode(-1);
		errorResponse.setResultCode("FAIL");
		errorResponse.setRequestUrl(req.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

	}

}
