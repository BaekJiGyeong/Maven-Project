package com.ktds.jgbaek.article.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.jgbaek.article.dao.ArticleDAO;
import com.ktds.jgbaek.article.vo.ArticleSearchVO;
import com.ktds.jgbaek.article.vo.ArticleVO;

public class ArticleDAOImpl extends SqlSessionDaoSupport implements ArticleDAO{

	@Override
	public int insertNewArticle(ArticleVO articleVO) {
		return getSqlSession().insert("ArticleDAO.insertNewArticle", articleVO );
	}

	@Override
	public int nextArticleSeq() {
		return getSqlSession().selectOne("ArticleDAO.nextArticleSeq");
	}

	@Override
	public String nowDate() {
		return getSqlSession().selectOne("ArticleDAO.nowDate");
	}

	@Override
	public int getTotalArticleCount() {
		return getSqlSession().selectOne("ArticleDAO.getTotalArticleCount");
	}

	@Override
	public List<ArticleVO> getAllArticle(ArticleSearchVO searchVO) {
		return getSqlSession().selectList("ArticleDAO.getAllArticle", searchVO);
	}

	@Override
	public ArticleVO getOneArticle(String articleId) {
		return getSqlSession().selectOne("ArticleDAO.getOneArticle", articleId);
	}

	@Override
	public void deleteArticle(String articleId) {
		getSqlSession().selectOne("ArticleDAO.deleteArticle", articleId);
	}

	@Override
	public int doModifyAction(ArticleVO articleVO) {
		return getSqlSession().insert("ArticleDAO.doModifyAction", articleVO );
	}
	
	
	
	
	
}
