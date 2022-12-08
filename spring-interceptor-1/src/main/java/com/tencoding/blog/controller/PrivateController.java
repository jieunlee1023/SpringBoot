package com.tencoding.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.annotation.AuthUser;

@AuthUser
@RestController
@RequestMapping("/admin")
public class PrivateController {

	@GetMapping("/test")
	public String test() {
		return "통장 계좌 : 1234-1234-1234";
	}
}
