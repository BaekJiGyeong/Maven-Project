package com.ktds.jgbaek.mongo.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ktds.jgbaek.mongo.dao.Paging;


public class MemoListVO {

	private Paging paging;
	private List<Map> memoList;
	
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	public List<Map> getMemoList() {
		
		List<Map> memos = new ArrayList<Map>();
		memos.addAll(memoList);
		
		return memos;
	}
	public void setMemoList(List<Map> memoList) {
		
		List<Map> memos = new ArrayList<Map>();
		memos.addAll(memoList);
		
		this.memoList = memos;
	}
	
	
	
}
