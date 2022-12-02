package com.example.demo1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private String name;
	private int age;
	@JsonProperty("phone_number")
	private String phoneNumber; //phone_number

}
