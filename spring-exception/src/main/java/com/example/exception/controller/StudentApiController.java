package com.example.exception.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.dto.Student;

@RestController
@RequestMapping("/student")
@Validated
public class StudentApiController {

	@GetMapping("/select")
	public ResponseEntity<Student> requestStudent(@Min(100) @RequestParam Long id) {

		// id 값을 받아서 Service 객체에게 요청
		Student student = new Student();
		student.setName("아무개");
		student.setGrade(1);

		return null;
	}

	@PostMapping("/insert")
	public ResponseEntity<Student> post(@Valid @RequestBody Student student) {
		System.out.println(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(student);
	}

	// 기본값 application/x-www-form-urlencoded
	// key = value로 선언하면 mapping 해줌
	// (따로 RequestBody를 선언안하도 된다)
	@PostMapping("/insert2")
	public ResponseEntity<Student> post2(String name, Integer grade) {
		System.out.println("name : " + name);
		System.out.println("grade : " + grade);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

}
