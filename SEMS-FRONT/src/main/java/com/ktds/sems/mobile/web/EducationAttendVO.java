package com.ktds.sems.mobile.web;

import java.util.List;
import java.util.Map;

import com.ktds.sems.education.vo.EducationVO;

public class EducationAttendVO {

	private EducationVO eduInfo;
	private Map<String, List<String>> attends;
	
	public EducationVO getEduInfo() {
		return eduInfo;
	}
	public void setEduInfo(EducationVO eduInfo) {
		this.eduInfo = eduInfo;
	}
	public Map<String, List<String>> getAttends() {
		return attends;
	}
	public void setAttends(Map<String, List<String>> attends) {
		this.attends = attends;
	}
	
	
	
	
}
