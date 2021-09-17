package com.spring.advice;

import org.aspectj.lang.ProceedingJoinPoint;

public class AdviceBehavior {
	public void chikachika() {
		System.out.println("열심히 양치질을 합시다");
	}
	
	
	//joinPoint : arround
	public void chikachika(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("한번 닦았는데...");
		joinPoint.proceed();
		
		System.out.println("또 닦아??!!");
	}
}
