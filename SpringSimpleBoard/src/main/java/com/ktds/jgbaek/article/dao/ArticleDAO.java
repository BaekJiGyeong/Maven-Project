package com.ktds.jgbaek.article.dao;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.jgbaek.article.vo.ArticleSearchVO;
import com.ktds.jgbaek.article.vo.ArticleVO;

public interface ArticleDAO {
	/**
	 ************************************ 
	 ************************************
	 */
	public int nextArticleSeq();
	public String nowDate();
	public int insertNewArticle(ArticleVO articleVO);
	public int getTotalArticleCount();
	public List<ArticleVO> getAllArticle(ArticleSearchVO searchVO);
	public ArticleVO getOneArticle(String articleId);
	public void deleteArticle(String articleId);
	public int doModifyAction(ArticleVO articleVO);

}
