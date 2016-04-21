package com.ktds.jgbaek.article.service;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.jgbaek.article.vo.ArticleVO;

public interface ArticleService {

	public ModelAndView writeNewArticle(ArticleVO articleVO, Errors errors);
	public ModelAndView getAllArticleList(int pageNo);
	public ModelAndView getOneArticle(String articleId);
	public ModelAndView deleteArticle(String articleId);
	public ModelAndView modifyArticle(String articleId);
	public ModelAndView doModifyAction(ArticleVO articleVO, Errors errors);
}
