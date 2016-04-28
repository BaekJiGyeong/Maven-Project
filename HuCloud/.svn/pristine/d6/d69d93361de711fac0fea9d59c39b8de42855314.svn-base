package kr.co.hucloud.security.code.example.attack.check.password.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PasswordController {

	@RequestMapping("/attack/password")
	public ModelAndView checkPassword() {
		ModelAndView view = new ModelAndView("attack/password/password");
		return view;
	}
	
	@RequestMapping("/attack/verifyPassword")
	public ModelAndView verifyPassword(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("attack/password/password");
		
		String password = request.getParameter("password");
		String passwordPolicy = "((?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()]).{8,})";
		
		Pattern pattern = Pattern.compile(passwordPolicy);
		Matcher matcher = pattern.matcher(password);
		
		view.addObject("result", password + "는 " + (matcher.matches() ? "알맞은 비밀번호" : "취약한 비밀번호") + "입니다.");
		
		return view;
	}
	
	
	
}
