package com.example.pattern;

import org.springframework.stereotype.Component;

@Component("MintChocolateMaker")
public class MintChocolateMaker implements IChocolateMaker{

	@Override
	public String maker(String taste) {
		String mintChocolate = taste;
		return mintChocolate;
	}

}
