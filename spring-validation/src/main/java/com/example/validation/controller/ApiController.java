package com.example.validation.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.validation.dto.User;

@RestController
@RequestMapping("/api")
//@Validated //<-get 방식일 때 반드시 추가!!!
public class ApiController {

	@PostMapping("/user")
	// @Valid 을 사용해야 우리가 적용한 valid 처리를 한다.

	public ResponseEntity<?> user(@Valid @RequestBody User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors() == true) {
			System.out.println("1");
			StringBuffer sb = new StringBuffer();
			bindingResult.getAllErrors().forEach((error) -> {
				FieldError filed = (FieldError) error;
				String msg = error.getDefaultMessage();
				System.out.println(sb.toString());
				System.out.println("field : " + filed.getField());
				System.out.println("msg : " + msg);

				sb.append("field : " + filed.getField() + "\n");
				sb.append("msg : " + msg + "\n");
				

			});
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
		}
  
		// 예전 방식
//		if (user.getAge() < 1 || user.getAge() > 100) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
//		}
		System.out.println(user);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/user1")
	public User user(@Size(min = 2) @RequestParam String name,@Min(1) @RequestParam int age) {
		User user = new User();
		user.setName(name);
		user.setAge(age);
		return user;
	}

}
