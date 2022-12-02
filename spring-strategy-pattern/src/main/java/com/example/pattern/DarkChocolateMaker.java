package com.example.pattern;

import org.springframework.stereotype.Component;

@Component("DarkChocolateMaker")
public class DarkChocolateMaker implements IChocolateMaker{

	@Override
	public String maker(String taste) {
		String darkChocolate = taste;
		return darkChocolate;
	}

}
