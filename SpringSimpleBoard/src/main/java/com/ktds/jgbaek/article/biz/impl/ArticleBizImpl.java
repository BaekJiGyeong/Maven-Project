package com.ktds.jgbaek.article.biz.impl;

import java.util.List;

import com.ktds.jgbaek.article.biz.ArticleBiz;
import com.ktds.jgbaek.article.dao.ArticleDAO;
import com.ktds.jgbaek.article.vo.ArticleSearchVO;
import com.ktds.jgbaek.article.vo.ArticleVO;


public class ArticleBizImpl implements ArticleBiz {

	private ArticleDAO articleDAO;
	
	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	@Override
	public boolean writeNewArticle(ArticleVO articleVO) {
		
		// 만들지 않은 것은 던진다고 알려준다는 뜻 // throw new NotImplementedException();
		
		int nextArticleId = articleDAO.nextArticleSeq();
		String nowDate = articleDAO.nowDate();
		
		/**
		 * ArticleID의 형식
		 * AR-20160421-000001
		 */
		
		String articleId = "AR-" + nowDate + "-" + lpad(nextArticleId + "", 6, "0");
		
		articleVO.setArticleNumber(nextArticleId);
		articleVO.setArticleId(articleId);
		
		
		return articleDAO.insertNewArticle(articleVO) >0 ;
	}

	/**
	 * 
	 * @param source 원본
	 * @param length 만들어야 하는 길이
	 * @param defValue 채워질 글자
	 * @return
	 */
	private String lpad(String source, int length, String defValue) {
		
		/*
		 * 100 : 3
		 * 000100 : 6
		 */
		
		int sourceLength = source.length();
		int needLength = length -  sourceLength;
		
		for ( int i=0; i<needLength; i++ ) {
			source = defValue + source;
		}
		
		return source;
	}

	@Override
	public int getTotalArticleCount() {
		return articleDAO.getTotalArticleCount();
	}

	@Override
	public List<ArticleVO> getAllArticle(ArticleSearchVO searchVO) {
		return articleDAO.getAllArticle(searchVO);
	}

	@Override
	public ArticleVO getOneArticle(String articleId) {
		
		return articleDAO.getOneArticle(articleId);
	}

	@Override
	public void deleteArticle(String articleId) {
		articleDAO.deleteArticle(articleId);
	}

	@Override
	public boolean doModifyAction(ArticleVO articleVO) {
		
		String nowDate = articleDAO.nowDate();
		articleVO.setCreatedDate(nowDate);
		
		return articleDAO.doModifyAction(articleVO) >0 ;
	}
}
