package com.ktds.jgbaek.article.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class ArticleVO {
	
	private String articleId;
	private int articleNumber;
	
	@NotEmpty(message="제목을 입력해 주세요!")
	private String subject;
	
	@NotEmpty(message="작성자의 이름을 입력해 주세요!")
	private String writer;
	
	private String description;
	private String createdDate;
	private String modifiedDate;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public int getArticleNumber() {
		return articleNumber;
	}
	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	

}
