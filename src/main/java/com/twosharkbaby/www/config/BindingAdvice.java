package com.twosharkbaby.www.config;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import io.sentry.Sentry;

@Component
@Aspect
public class BindingAdvice {
	
	private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);

	@Around("execution(* com.twosharkbaby.www.controller..UserController.*(..))")
	public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName(); // 실행 클래스 위치명
		String method = proceedingJoinPoint.getSignature().getName();  // 실행 메서드 이름
		
		Object[] args = proceedingJoinPoint.getArgs();  // 모든 파라메타
		
		for(Object arg : args) {
			if(arg instanceof BindingResult) {  // 파라테마 중에 BindingResult 가 있으면 실행
				BindingResult bindingResult = (BindingResult) arg;
				// 프론트에서 null등을 처리하는데 에러가 발생한다면 올바르지 않은 접근방식임
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					for(FieldError error : bindingResult.getFieldErrors() ) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						log.warn(type+"."+method+"()=>필드:"+error.getField()+",메세지:"+error.getDefaultMessage());
						Sentry.captureMessage(type+"."+method+"()=>필드:"+error.getField()+",메세지:"+error.getDefaultMessage());				
					}
					return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
				}
			}
		}
		return proceedingJoinPoint.proceed();  // 문제가 없으면 나머지 로직을 수행
	}
	
}
