package com.ktds.jgbaek.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@RequestMapping("/article/{articleNumber}")
	public ModelAndView article(@PathVariable int articleNumber) {
		ModelAndView view = new ModelAndView();
		view.setViewName("article/article");
		view.addObject("articleNumber", articleNumber);
		return view;
	}
}
