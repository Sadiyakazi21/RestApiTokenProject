package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.Controller.Home;

@SpringBootApplication
public class SpringSecurityApplication {

	
	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(SpringSecurityApplication.class);
	logger.info("Application Started successfully ");
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
