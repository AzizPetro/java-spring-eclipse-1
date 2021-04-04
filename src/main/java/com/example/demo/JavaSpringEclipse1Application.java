package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"controller"})
public class JavaSpringEclipse1Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringEclipse1Application.class, args);
	}

}
