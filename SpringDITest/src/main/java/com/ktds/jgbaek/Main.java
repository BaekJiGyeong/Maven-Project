package com.ktds.jgbaek;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		// applicationContext.xml 안에 정의되어있는 bean 들을 모두 객체로 생성한다.
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		
		LoginBiz loginBiz = (LoginBiz)ctx.getBean("loginBiz");
		System.out.println(loginBiz.login());
	}

}
