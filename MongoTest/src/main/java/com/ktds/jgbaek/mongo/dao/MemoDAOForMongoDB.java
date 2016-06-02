package com.ktds.jgbaek.mongo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.ktds.jgbaek.mongo.vo.MemoListVO;


public class MemoDAOForMongoDB implements MemoDAO {
	
	private MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	 @Override
	   public void insertMemo(String memo) {
	      Map<String, String> memoMap = new HashMap<String, String>();
	      
	      memoMap.put("memo", memo);
	      
	      mongoTemplate.insert(memoMap, "memo");
	   }

	@Override
	public void updateMemo(String id, String memo) {
		Criteria criteria = new Criteria("_id");
		criteria.is(id);
		
		Query query = new Query(criteria);
		
		Update update = new Update();
		update.set("memo", memo);
		
		mongoTemplate.updateMulti(query, update, "memo");		
	}

	@Override
	public void removeMemo(String id) {
		Criteria criteria = new Criteria("_id");
		criteria.is(id);
		
		Query query = new Query(criteria);
		
		mongoTemplate.remove(query, "memo");
		
	}

//	@Override
//	public List<Map> getMemoList() {
//		
//		SkipOperation skip = Aggregation.skip(paging.getStartArticleNumber());
//		LimitOperation limit = Aggregation.limit(paging.getEndArticleNumber());
//		
//		//
//		Aggregation aggregation = Aggregation.newAggregation(skip, limit);
//		AggregationResults<Map> result =
//				mongoTemplate.aggregate(aggregation, "memo", Map.class);
//		//
//		
//		return mongoTemplate.find(new Query(), Map.class, "memo");
////		return (List<Map>) mongoTemplate.aggregate(aggregation, "memo", Map.class);
//	}
	@Override
	public MemoListVO getMemoList(int pageNo) {
		MemoListVO memoListVO = new MemoListVO();
		Paging paging = new Paging(false);
		memoListVO.setPaging(paging);
		
		paging.setPageNumber(pageNo+"");
		
		paging.setTotalArticleCount(10000);
		
		SkipOperation skip = Aggregation.skip(paging.getStartArticleNumber());
		LimitOperation limit = Aggregation.limit(paging.getEndArticleNumber());
		
		//
		Aggregation aggregation = Aggregation.newAggregation(skip, limit);
		AggregationResults<Map> result =
				mongoTemplate.aggregate(aggregation, "memo", Map.class);
		//
		
		List<Map> memoList = result.getMappedResults();
		memoListVO.setMemoList(memoList);
		
		System.out.println("********");
		for (Map map : memoList) {
			System.out.println(map.get("memo"));
		}
		
		return memoListVO;
	}

	@Override
	public Map getMemo(String id) {
		Criteria criteria = new Criteria("_id");
		criteria.is(id);
		
		Query query = new Query(criteria);
		
		return mongoTemplate.findOne(query, Map.class, "memo");
	}
	
	
	
}
