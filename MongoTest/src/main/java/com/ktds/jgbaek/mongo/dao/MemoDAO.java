package com.ktds.jgbaek.mongo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import com.ktds.jgbaek.mongo.vo.MemoListVO;

public interface MemoDAO {
	
	public void insertMemo(String memo);
	public void updateMemo(String id, String memo);
	public void removeMemo(String id);
	public MemoListVO getMemoList(int pageNo);
	public Map getMemo(String id);
	
}
