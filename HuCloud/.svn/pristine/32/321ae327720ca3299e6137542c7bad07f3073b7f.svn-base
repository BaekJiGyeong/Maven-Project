package kr.co.hucloud.security.code.example.attack.cookie.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {

	@RequestMapping("/attack/cookie")
	public String cookie(HttpServletResponse response) {
		return "attack/cookie/cookie";
	}
	
	@RequestMapping("/attack/cookie/method1")
	public String cookieMethod1(HttpServletRequest request, HttpServletResponse response) {
		
		String text = request.getParameter("text");
		// FIXME Cookie 등록
		
		return "attack/cookie/cookie";
	}
	
	@RequestMapping("/attack/cookie/method2")
	public String cookieMethod2(HttpServletRequest request, HttpServletResponse response) {
		
		String text = request.getParameter("text");
		// FIXME Cookie 등록
		// SSL Mode 에서 동작하도록 함
		
		return "attack/cookie/cookie";
	}
	
}
