package com.example.pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringStrategyPattern2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringStrategyPattern2Application.class, args);
		
		ApplicationContext context = ApplicationContextProvider.getContext();
		
		StrawberryChocolateMaker strawberryChocolateMaker = context.getBean(StrawberryChocolateMaker.class);
		DarkChocolateMaker darkChocolateMaker = context.getBean(DarkChocolateMaker.class);
		MintChocolateMaker mintChocolateMaker = context.getBean(MintChocolateMaker.class);
		ChocolateMaker chocolateMaker = context.getBean(ChocolateMaker.class);
		
		String StrawberryChocolate = chocolateMaker.makeChocolate("딸기");
		System.out.println(StrawberryChocolate);
		
		chocolateMaker.setIChocolateMaker(darkChocolateMaker);
		String darkChocolate = chocolateMaker.makeChocolate("다크");
		System.out.println(darkChocolate);
		
		chocolateMaker.setIChocolateMaker(mintChocolateMaker);
		String mintChocolate = chocolateMaker.makeChocolate("민트");
		System.out.println(mintChocolate);
	}

}
