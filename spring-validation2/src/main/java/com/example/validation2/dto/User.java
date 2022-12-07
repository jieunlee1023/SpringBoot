package com.example.validation2.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

	@NotBlank(message = "이름을 입력하라")
	private String name;
	@Max(value = 100, message = "100세 이하로 입력하라")
	private int age;
	@Email
	private String email;
	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다.")
	private String phoneNumber;
	@Size(max = 6, min = 6)
	private String reqYearMonth;

	// custom validation 을 적용 하여 보자
	// 주의 !! return 타입이 boolean 이면 메서드 이름 시작을 반드시 is로 하여야 한다 !! 약속
	// 들어오는 형식은 yyyyMM 형식이지만 DB ==> yyyy-MM (저장 처리를 해야한다.)
	@AssertTrue(message = "yyyyMM 형식이 아닙니다")
	public boolean isReqYearMonth() {
		System.out.println("여기 코드가 동작 하나요?");
		// 로직 구현 가능
		// 구분자 값이 있거나 구분해서 값을 넣어주면 LocalDate 객체를 생성할 수 있다.
		// 555555

		// 5555

		try {

			int tempYear = Integer.parseInt(getReqYearMonth().substring(0, 4));
			int tempMonth = Integer.parseInt(getReqYearMonth().substring(4, 6));
			System.out.println("tempYear : " + tempYear);
			System.out.println("tempMonth : " + tempMonth);

			// 2022-12-01
			LocalDate date = LocalDate.of(tempYear, tempMonth, 01);
			String convertReqYearMonth = date.format(DateTimeFormatter.ofPattern("yyyy-MM"));
			System.out.println(date);
			System.out.println("------------------------------------");
			System.out.println(convertReqYearMonth);
			System.out.println("------------------------------------");
			
			
			// false <--date가 기준이다.
			boolean isAfter = date.isAfter(LocalDate.of(2023, 11, 01));
			System.out.println("isAfter : "+isAfter);
			
			boolean isBefore = date.isBefore(LocalDate.of(2023, 11, 01));
			System.out.println("isBefore : "+isBefore);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}