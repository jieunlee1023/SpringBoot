package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Employee;
import com.example.demo.utils.MObjectConverter;

@RestController
@RequestMapping("/api")
public class APIController {

	// Response 방법에 대한 이해
	// MINE TYPE : text/plain
	@GetMapping("/article")
	public String article(@RequestParam String text) {
		return text;
	}
	
	// Object type을 리턴하면 json으로 자동 변환 처리

	// MINE TYPE : application/json
	// request(json) -> objectMepper--> Object 변환처리함
	// response(object) -> objectMapper -> json 문자형식 변환함
	@PostMapping("/emp")
	public Employee returnSelfEmployee(@RequestBody Employee employee) {
		//--> object 형식으로 들어와서
		employee.setEmpNo("11111"); // <-- object라 .연산자 사용가능
		return employee;
		//--> json 형식으로 보냄
	}
	
	
	//ResponseEntity의 이해
	// 201번으로 결과를 받고 싶을 때
	@PutMapping("/emp")
	public ResponseEntity<Employee> put(@RequestBody Employee employee) {
		employee.setEmpNo("11111");
		employee.setCode(1);
		employee.setMassage("리소스 갱신에 성공했습니다!");
		return ResponseEntity.status(HttpStatus.CREATED).body(employee);
	}
	

	// 생성된 객체가 있다면 json 형식의 문자열을 만드는 샘플 코드를 굳이 만들어보자
	@GetMapping("/test1") // http://localhost:8080/api/test1
	public String makeJsonOfObject() {
		Employee employee = Employee.builder()
				.empNo("10001")
				.birthDate("1996-10-23")
				.firstName("지은")
				.lastName("이")
				.hireDate("1999-01-01")
				.gender("여")
				.build();
		String json = "";
		
		MObjectConverter<Employee> converter = new MObjectConverter();
		json = converter.objectToJson(employee);
		
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			json = mapper.writeValueAsString(employee);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return json;

	}
}
