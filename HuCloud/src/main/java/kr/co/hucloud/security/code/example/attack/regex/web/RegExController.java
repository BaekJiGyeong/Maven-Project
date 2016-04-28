package kr.co.hucloud.security.code.example.attack.regex.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegExController {

	@RequestMapping("/attack/regex")
	public String regex(HttpServletResponse response) {
		return "attack/regex/regex";
	}
	
	@RequestMapping("/attack/regex/method1")
	public ModelAndView method1(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("attack/regex/regex");
		
		String text = request.getParameter("text");
		view.addObject("input1", text);
		
		//FIXME 첫 글자와 끝 글자가 숫자로 끝나는 정규표현식 작성
		
		view.addObject("result1", "유효하지 않은 입력값");
		
		return view;
	}
	
	@RequestMapping("/attack/regex/method2")
	public ModelAndView method2(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("attack/regex/regex");
		
		String text = request.getParameter("text");
		view.addObject("input2", text);
		
		//FIXME kim:010-1111-2222:kim과 같이 첫 번째 세 번째 값이 같고, 
		//두 번째 값은 숫자와 -로만 구성되어 있는지 확인하는 정규표현식 작성
		
		view.addObject("result2", "유효하지 않은 입력값");
		
		return view;
	}
	
	@RequestMapping("/attack/regex/method3")
	public ModelAndView method3(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("attack/regex/regex");
		
		String text = request.getParameter("text");
		view.addObject("input3", text);
		
		//FIXME <script> ... </script> 패턴이면 <를 &lt;로, >를 &gt;로 변환하는 정규표현식 작성
		
		view.addObject("result3", text);
		
		return view;
	}
	
}
