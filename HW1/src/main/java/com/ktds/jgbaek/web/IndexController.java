package com.ktds.jgbaek.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.jgbaek.vo.LoginVO;

@Controller
public class IndexController {

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String index() {
		return "login";
	}
	
/*	
	@RequestMapping("/doLogin")
	public ModelAndView login(HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("doLogin");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		view.addObject("id", id);
		view.addObject("password", password);
		return view;
	}
*/
/*	
	@RequestMapping("/doLogin")
	public ModelAndView login(@RequestParam("id") String id, @RequestParam("password") String password) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("doLogin");
		
		view.addObject("id", id);
		view.addObject("password", password);
		return view;
	}
*/
	@RequestMapping("/doLogin")
	public ModelAndView login(LoginVO loginVO) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("doLogin");
		return view;
	}
	
	
}
