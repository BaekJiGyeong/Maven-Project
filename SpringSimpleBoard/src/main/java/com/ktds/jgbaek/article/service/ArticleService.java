package com.ktds.jgbaek.article.service;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.jgbaek.article.vo.ArticleVO;

public interface ArticleService {

	public ModelAndView writeNewArticle(ArticleVO articleVO, Errors errors);
}
