package com.ktds.jgbaek.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.jgbaek.biz.ArticleBiz;
import com.ktds.jgbaek.vo.EmployeesVO;

@Controller
public class ArticleController {
	// Controller를 Servlet이라고 생각하자.
	
	
	// logger는 항상 제일 위에 있어야한다.
	private Logger logger= LoggerFactory.getLogger(ArticleController.class);

	private ArticleBiz articleBiz;
	
	public void setArticleBiz(ArticleBiz articleBiz) {
		this.articleBiz = articleBiz;
	}
	
	@RequestMapping("/list")
	public ModelAndView articleList() {
		
		articleBiz.insertNewArticle();
		
		logger.trace("안녕하세요 트레이스입니다.");
		logger.debug("안녕하세요 디버그입니다.");
		logger.info("안녕하세요 인포입니다.");
		logger.warn("안녕하세요 워닝입니다.");
		logger.error("안녕하세요 에러입니다.");
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/article/list");
		
		List<EmployeesVO> allEmployees = articleBiz.getAllEmployeesInfo();
		view.addObject("allEmployees", allEmployees);
		
		// request.setAttribute("Key", "Value");
		view.addObject("title", "제목");
		view.addObject("number", "1");
		view.addObject("author", "홍길동");
		
		return view;
	}
	
	@RequestMapping("/detail/{articleNumber}")
	public ModelAndView detail(@PathVariable int articleNumber) {
		ModelAndView view = new ModelAndView();
		view.setViewName("article/detail");
		view.addObject("articleNumber", articleNumber);
		return view;
	}
}
