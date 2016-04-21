package com.ktds.jgbaek.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.ktds.jgbaek")
public class CustomExceptionHandler {
	
	// {}는 배열이라는 뜻
	@ExceptionHandler({RuntimeException.class})
	public ModelAndView RuntimeExceptionHandler(RuntimeException re, HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("error/500");
		view.addObject("message", re.getMessage());
		
		// 이페이지를 호출한 직전페이지
		String referer =request.getHeader("Referer");
		view.addObject("from", referer);
		
		
		view.addObject("content","내가 보냄....");
		return view;
	}

}
