package kr.co.hucloud.security.code.example.attack.nullpointer.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NullPointerController {

	@RequestMapping("/attack/nullpointer")
	public String nulls(HttpServletResponse response) {
		return "attack/nullpointer/nullpointer";
	}
	
	@RequestMapping("/attack/nullpointer/test")
	public ModelAndView nulls(HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("attack/nullpointer/nullpointer");
		
		String text = request.getParameter("text");
		//FIXME NullPointerException 발생 방지
		if (text.length() > 5) {
			text += "은(는) 5글자 이상입니다.";
		}
		else {
			text += "은(는) 5글자 이하입니다.";
		}
		view.addObject("result", text);
		
		return view;
	}
}
