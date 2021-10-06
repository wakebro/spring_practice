package org.ict.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Component
@Log4j
public class LogAdvice {
	
	@Before("execution(* org.ict.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("==================");
	}
}
