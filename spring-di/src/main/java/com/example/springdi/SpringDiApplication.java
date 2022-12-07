package com.example.springdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringDiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDiApplication.class, args);

		MyBase64Encoder base64Encoder = new MyBase64Encoder();
		MyUrlEncoder urlEncoder = new MyUrlEncoder();

		String mUrl = "www.naver.com/q?오늘축구이기나?";

		MyEncoder encoder = new MyEncoder(base64Encoder);
		String result1 = encoder.encode(mUrl);
		System.out.println("base64Encoder : " + result1);

		MyEncoder myEncoder = new MyEncoder(urlEncoder);
		String result2 = myEncoder.encode(mUrl);
		System.out.println("urlEncoder : " + result2);

		MyAsonEncoder asonEncoder = new MyAsonEncoder();
		MyEncoder encoder2 = new MyEncoder(asonEncoder);
		String result3 = encoder2.encode(mUrl);
		System.out.println("asonEncoder : " + result3);
	}

}
@Configuration
class MyAnimal {
	
	Dog dog;
	Cat cat;
	
	@Bean
	public Dog getDog() {
		return dog;
	}
	public void setDog(Dog dog) {
		this.dog = dog;
	}
	@Bean
	public Cat getCat() {
		return cat;
	}
	public void setCat(Cat cat) {
		this.cat = cat;
	}

}

class Dog {

}

class Cat {

}