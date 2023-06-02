package com.twosharkbaby.www.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
public class MyExcoptionHandler {

	@ExceptionHandler(value = Exception.class)
	public String exception(Exception e) {
		return "error_page";
	}
	
}
