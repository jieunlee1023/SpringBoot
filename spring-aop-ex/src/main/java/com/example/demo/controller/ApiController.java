package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.anno.AddHeart;
import com.example.demo.anno.Timer;
import com.example.demo.dto.User;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Timer
	@AddHeart
	@GetMapping("/get/{id}")
	public String get(@PathVariable String id) {
		return id+"성공!";
	}
	
	@Timer
	@AddHeart
	@PostMapping("/post")
	public ResponseEntity<User> post(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@Timer
	@AddHeart
	@DeleteMapping("/delete")
	public void delete() throws InterruptedException {
		Thread.sleep(3000);
	}
	
	
}
