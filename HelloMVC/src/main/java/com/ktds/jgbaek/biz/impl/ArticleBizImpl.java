package com.ktds.jgbaek.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.jgbaek.biz.ArticleBiz;
import com.ktds.jgbaek.dao.ArticleDAO;
import com.ktds.jgbaek.vo.EmployeesVO;

public class ArticleBizImpl implements ArticleBiz {

	   private Logger logger = LoggerFactory.getLogger(ArticleBiz.class);
	   
	   private ArticleDAO articleDAO;
	   
	   public void setArticleDAO(ArticleDAO articleDAO) {
	      this.articleDAO = articleDAO;
	   }

	   @Override
	   public void insertNewArticle() {	      
	      logger.debug("System Were InsertNewArticle Response.");
	      String nowDate = articleDAO.getNowSystemDate();
	      logger.info("Now Time is {} .", nowDate);
	   }
	   
	   @Override
	   public List<EmployeesVO> getAllEmployeesInfo() {
		   return articleDAO.getAllEmployeeInfo();
	   }

}
