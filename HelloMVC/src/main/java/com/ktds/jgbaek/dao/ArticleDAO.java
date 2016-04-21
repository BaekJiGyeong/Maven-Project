package com.ktds.jgbaek.dao;

import java.util.List;

import com.ktds.jgbaek.vo.EmployeesVO;

public interface ArticleDAO {
	
	public String getNowSystemDate();
	public List<EmployeesVO> getAllEmployeeInfo();

}
