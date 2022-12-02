package com.example.pattern;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ChocolateMaker {

	private IChocolateMaker iChocolateMaker;

	public ChocolateMaker(@Qualifier("StrawberryChocolateMaker") IChocolateMaker iChocolateMaker) {
		this.iChocolateMaker = iChocolateMaker;
	}

	public void setIChocolateMaker(IChocolateMaker iChocolateMaker) {
		this.iChocolateMaker = iChocolateMaker;
	}

	// 기능 초콜릿 만들기
	public String makeChocolate(String taste) {
		return taste + "맛 초콜릿 만들기!";
	}

}
