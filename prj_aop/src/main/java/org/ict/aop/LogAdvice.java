package org.ict.aop;


import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Component
@Log4j
public class LogAdvice {
	
	
	// 1번 advice
	@Before("execution(* org.ict.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("==================");
	}
	
	
	// 2번 advice
	@Before("execution(* org.ict.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1 : " + str1);
		log.info("str2 : " + str2);
	}
	
	
	// 3번 advice - 예외가 발생했을 때 작동
	@AfterThrowing(pointcut = "execution(* org.ict.service.SampleService*.*(..))", throwing = "exception")
	public void logException(Exception exception) {
		log.info("Exception.....!!!!");
		log.info("exception : " + exception);
	}
	
	// 4번 메서드 실행되는 시간동안 진행
	@Around("execution(* org.ict.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		
		// 시작 시간 기록
		long start = System.currentTimeMillis();
		
		log.info("Target : " + pjp.getTarget());
		log.info("Param : " + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		try {
			// 실제 메서드 실행
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		// 끝 시간 기록
		long end = System.currentTimeMillis();
		
		// 소요시간
		log.info("Time : " + (end - start));
		
		return result;
	}
	
}
