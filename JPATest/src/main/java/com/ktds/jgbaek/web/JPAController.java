package com.ktds.jgbaek.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ktds.jgbaek.service.JPAService;

@Controller
public class JPAController {
	
	private JPAService jpaService;
	
	public void setJpaService(JPAService jpaService) {
		this.jpaService = jpaService;
	}

	@RequestMapping("/insert")
	public void insertData(){
		jpaService.insertData();
	}
}
