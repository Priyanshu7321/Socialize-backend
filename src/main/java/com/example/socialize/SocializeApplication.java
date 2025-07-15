package com.example.socialize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SocializeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocializeApplication.class, args);
	}

}
