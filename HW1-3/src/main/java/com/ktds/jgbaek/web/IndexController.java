package com.ktds.jgbaek.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.jgbaek.vo.ArticleVO;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index() {
		return "mainPage";
	}
/*
	@RequestMapping("/modify/{articleId}")
	public ModelAndView login(HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("modify");
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		view.addObject("subject", subject);
		view.addObject("content", content);
		return view;
	}
*/
/*	
	@RequestMapping("/modify/{articleId}")
	public ModelAndView login(@RequestParam("subject") String subject, @RequestParam("content") String content) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("modify");
		
		view.addObject("subject", subject);
		view.addObject("content", content);
		return view;
	}
*/
	@RequestMapping("/modify/{articleId}")
	public ModelAndView login(ArticleVO articleVO) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("modify");
		return view;
	}
	
}
