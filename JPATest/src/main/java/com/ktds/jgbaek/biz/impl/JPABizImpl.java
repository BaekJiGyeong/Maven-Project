package com.ktds.jgbaek.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.ktds.jgbaek.biz.JPABiz;
import com.ktds.jgbaek.domain.JPA;
import com.ktds.jgbaek.domain.JPA2;
import com.ktds.jgbaek.repository.JPARepository;

public class JPABizImpl implements JPABiz{

	private JPARepository jpaRepository;
	
	public void setJpaRepository(JPARepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	@Override
	public JPA insertData() {
		JPA jpa= new JPA();
		jpa.setSubject("JPA Test");
		jpa.setDescription("JPA Desctiprion");
		
		JPA2 jpa2 = new JPA2();
		jpa2.setMemo("반갑습니다..");
		
		List<JPA2> jpa2List = new ArrayList<JPA2>();
		jpa2List.add(jpa2);
		
		jpa.setJpa2(jpa2List);
		
		jpaRepository.save(jpa);
		
		System.out.println(jpa.getSubject());
		System.out.println(jpa.getDescription());
		System.out.println(jpa.getId());
		
		return jpa;
	}

	@Override
	public JPA updateData(JPA jpa) {
		jpaRepository.save(jpa);
		
		JPA jpas = jpaRepository.findOne(7);
		return jpa;
	}

}
