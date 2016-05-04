package com.ktds.jgbaek.service.impl;

import com.ktds.jgbaek.biz.JPABiz;
import com.ktds.jgbaek.domain.JPA;
import com.ktds.jgbaek.service.JPAService;

public class JPAServiceImpl implements JPAService{

	private JPABiz jpaBiz;
	
	public void setJpaBiz(JPABiz jpaBiz) {
		this.jpaBiz = jpaBiz;
	}

	@Override
	public void insertData() {
		JPA jpa = jpaBiz.insertData();
		
		jpa.setSubject("수정합니다...");
		jpaBiz.updateData(jpa);
	}
	
}
