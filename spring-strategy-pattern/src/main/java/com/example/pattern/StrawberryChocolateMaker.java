package com.example.pattern;

import org.springframework.stereotype.Component;

@Component("StrawberryChocolateMaker")
public class StrawberryChocolateMaker implements IChocolateMaker {

	@Override
	public String maker(String taste) {
		String strawberryChocolate = taste;
		return strawberryChocolate;
	}

}
