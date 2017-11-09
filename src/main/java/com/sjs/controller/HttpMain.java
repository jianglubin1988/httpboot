package com.sjs.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAutoConfiguration
public class HttpMain {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public String home() {
		return "Hello, Spring Boot";
	}

	public static void main(String[] args) {
		SpringApplication.run(HttpMain.class, args);
	}
	
}
