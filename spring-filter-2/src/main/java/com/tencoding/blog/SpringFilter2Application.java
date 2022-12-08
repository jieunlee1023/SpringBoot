package com.tencoding.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringFilter2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringFilter2Application.class, args);
	}

}
