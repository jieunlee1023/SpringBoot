package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;

// page를 응답할 때 사용한다.
// (html, jsp, time leaf, mustch, ... )
@Controller
public class PageController {
	
	@GetMapping("/main")
	public String main() {
		//view resolver 동작으로 파일을 찾아준다.
		return "main.html";
	}
	
	// 만약 json을 리턴하고 싶다면 ?
	@ResponseBody
	@GetMapping("/employee")

	public Employee employee() {
		var emp = new Employee();
		emp.setEmpNo("100");
		emp.setFirstName("지은");
		emp.setLastName("이");

		return emp;
	}
}
