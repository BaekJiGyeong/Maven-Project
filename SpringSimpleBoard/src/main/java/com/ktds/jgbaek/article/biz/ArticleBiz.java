package com.ktds.jgbaek.article.biz;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.jgbaek.article.vo.ArticleSearchVO;
import com.ktds.jgbaek.article.vo.ArticleVO;

public interface ArticleBiz {
	
	public boolean writeNewArticle(ArticleVO articleVO);
	public int getTotalArticleCount();
	public List<ArticleVO> getAllArticle(ArticleSearchVO searchVO);
	public ArticleVO getOneArticle(String articleId);
	public void deleteArticle(String articleId);
	public boolean doModifyAction(ArticleVO articleVO);
	

}
