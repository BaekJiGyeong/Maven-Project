package com.ktds.jgbaek.article.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.jgbaek.article.service.ArticleService;
import com.ktds.jgbaek.article.vo.ArticleVO;

@Controller
public class ArticleController {
	
	private ArticleService articleService;
	
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping("/write")
	public ModelAndView viewWritePage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("article/write");
		return view;
	}
	
	@RequestMapping("/doWriteAction")
	public ModelAndView doWriteAction(@Valid ArticleVO articleVO, Errors errors) {
		return articleService.writeNewArticle(articleVO, errors);
	}
	
	@RequestMapping("/list")
	public ModelAndView viewListPage(@RequestParam(required=false, defaultValue="0") int pageNo) {
		return articleService.getAllArticleList(pageNo);
	}
	
	@RequestMapping("/detail/{articleId}")
	public ModelAndView viewDetailPage(@PathVariable String articleId) {
		return articleService.getOneArticle(articleId);
	}
	
	@RequestMapping("/delete/{articleId}")
	public ModelAndView deleteArticle(@PathVariable String articleId) {
		return articleService.deleteArticle(articleId);
	}
	
	@RequestMapping("/modify/{articleId}")
	public ModelAndView modifyArticle(@PathVariable String articleId) {
		return articleService.modifyArticle(articleId);
	}
	
	@RequestMapping("/doModifyAction")
	public ModelAndView doModifyAction(@Valid ArticleVO articleVO, Errors errors) {
		return articleService.doModifyAction(articleVO, errors);
	}
	
	
	

}
