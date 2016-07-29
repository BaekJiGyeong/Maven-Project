package com.ktds.sems.member.vo;

import java.util.List;
import java.util.Map;

import kr.co.hucloud.utilities.web.Paging;

public class AttendListVO {
	
	private Paging paging;
	private Map<String, List<String>> attends;
	
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	public Map<String, List<String>> getAttends() {
		return attends;
	}
	public void setAttends(Map<String, List<String>> attends) {
		this.attends = attends;
	}
}
