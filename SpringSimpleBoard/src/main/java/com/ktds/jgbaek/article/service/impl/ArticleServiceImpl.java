package com.ktds.jgbaek.article.service.impl;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.jgbaek.article.service.ArticleService;
import com.ktds.jgbaek.article.vo.ArticleVO;

public class ArticleServiceImpl implements ArticleService{
	
	@Override
	public ModelAndView writeNewArticle(ArticleVO articleVO, Errors errors){
		ModelAndView view= new ModelAndView();
		
		if ( errors.hasErrors() ) {
			view.setViewName("article/write");
			view.addObject("articleVO", articleVO);
			return view;
		}
		else {
			// TODO biz 호출
			view.setViewName("redirect:/list"); // 없다면 list로 가라
		}
		
		return view;
	}

}
