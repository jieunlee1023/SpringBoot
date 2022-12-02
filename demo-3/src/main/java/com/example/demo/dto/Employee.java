package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@EqualsAndHashCode(callSuper = false) //상속시 선언 룸북을 사용하게 되면 처리해주는 코드
@JsonInclude(JsonInclude.Include.NON_NULL) // null 값을 없애준다
public class Employee extends CommonRes {
	//TRM 방식 모델링 처리 
	//(Table(mysql의 테이블) R..M..)

	private String empNo;
	private String birthDate;
	private String firstName;
	private String lastName;
	private String gender;
	private String hireDate;

}
