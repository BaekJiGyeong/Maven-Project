package com.ktds.jgbaek.article.vo;

import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class ArticleListVO {

	private Paging paging;
	private List<ArticleVO> articleList;
	
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	public List<ArticleVO> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<ArticleVO> articleList) {
		this.articleList = articleList;
	}
	
	
	
}
