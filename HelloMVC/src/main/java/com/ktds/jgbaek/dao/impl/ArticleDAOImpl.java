package com.ktds.jgbaek.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.jgbaek.biz.ArticleBiz;
import com.ktds.jgbaek.dao.ArticleDAO;
import com.ktds.jgbaek.vo.EmployeesVO;

public class ArticleDAOImpl extends SqlSessionDaoSupport implements ArticleDAO{

	private Logger logger = LoggerFactory.getLogger(ArticleBiz.class);
	
	private ArticleDAO articleDAO;
	
	//반드시 setter가 있어야함
	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}
	@Override
	public String getNowSystemDate() {
		return getSqlSession().selectOne("ArticleDAO.getNowSystemDate");
		
	}
	
	@Override
	public List<EmployeesVO> getAllEmployeeInfo() {
		// String lastName = "King";
		
		Map<String, Object> parameters = new HashMap<String, Object>();
//		parameters.put("firstName", "Steven");
//		parameters.put("lastName", "King");
		// parameters.put("other", new EmployeesVO()); 이런것도 가능하다.
		
		EmployeesVO employee = new EmployeesVO();
		//employee.setFirstName("Steven");
		//employee.setLastName("King");
		
		List<Integer> managerId = new ArrayList<Integer>();
		managerId.add(100);
		managerId.add(101);
		managerId.add(102);
		managerId.add(103);
		
		parameters.put("employee", employee);
		parameters.put("managerIds", managerId);
		
		return getSqlSession().selectList("ArticleDAO.getAllEmployeeInfo", parameters);
	}
	
}
