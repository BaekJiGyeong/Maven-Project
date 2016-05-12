package com.ktds.sems.education.vo;

import java.util.ArrayList;
import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class EducationListVO {
	
	private List<EducationVO> educationList;
	private Paging paging;
	
	public List<EducationVO> getEducationList() {
		List<EducationVO> educations = new ArrayList<EducationVO>();
		educations.addAll(educationList);
		return educations;
	}
	
	public void setEducationList(List<EducationVO> articleList) {
		List<EducationVO> educations = new ArrayList<EducationVO>();
		educations.addAll(articleList);
		
		this.educationList = educations;
	}
	
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
}
