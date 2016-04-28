package kr.co.hucloud.security.code.example.attack.exceptions.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionsController {

	@RequestMapping("/attack/exception")
	public String exception(HttpServletResponse response) {
		return "attack/exceptions/exceptions";
	}
	
	@RequestMapping("/attack/exception/printexception")
	public ModelAndView printException(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("attack/exceptions/exceptions");
		
		String text = request.getParameter("text");
		//FIXME 예외처리 필요함
		int number = Integer.parseInt(text);
		
		view.addObject("input1", text);
		view.addObject("result1", number);
		return view;
	}
	
	@RequestMapping("/attack/exception/passexception")
	public ModelAndView passException(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("attack/exceptions/exceptions");
		
		String text = request.getParameter("text");
		view.addObject("input2", text);
		
		try {
			int number = Integer.parseInt(text);
			view.addObject("result2", number);
		}
		catch(NumberFormatException nfe) {
			//FIXME 에러에 따른 처리가 필요함. 예외를 던지든, Return을 시키든 조치가 필요함.
			//web.xml 의 error-page 추가
		}
		
		view.addObject("result2_1", "에러가 발생했다면 이 메시지가 보이지 않아야 함.");
		return view;
	}
	
}
