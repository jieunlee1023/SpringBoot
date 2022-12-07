package com.example.validation2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.validation2.dto.User;

@RestController
@RequestMapping("/api")
public class APIController {

	@GetMapping("/user")
	public void get() {

	}

	@PostMapping("/user")
	public User post(@Valid @RequestBody User user, HttpServletRequest req) {
		System.out.println("req url : " + req.getRequestURI());
		return user;
	}
}