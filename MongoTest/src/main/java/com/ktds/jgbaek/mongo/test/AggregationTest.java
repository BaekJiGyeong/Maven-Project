package com.ktds.jgbaek.mongo.test;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;


public class AggregationTest {
	
	private MongoTemplate mongoTemplate;
	
	public AggregationTest() {
		
		String mongoContextPath = "/mongoContext.xml";
		
		AbstractApplicationContext ctx = 
				new ClassPathXmlApplicationContext(mongoContextPath);
		
		mongoTemplate = (MongoTemplate) ctx.getBean("mongoTemplate");
	}
	
	public static void main(String[] args) {
		AggregationTest mongoTest = new AggregationTest();
		
//		mongoTest.findData("name", "도우너");
//		mongoTest.pagingData("name", "둘리", 2);
		mongoTest.groupData();
	}
	
	private void findData(String key, String value) {
		
		Criteria criteria = new Criteria(key);
		criteria.is(value);
		
		// ************************패턴에서 이부분만 바뀜
		MatchOperation match = Aggregation.match(criteria);		
		
		// 정렬하기
		SortOperation sort = Aggregation.sort(Sort.Direction.DESC, "_id");
		
		// 건너뛰기
		SkipOperation skip = Aggregation.skip(2);
		
		// 지정한 수 만큼 가져오기
		LimitOperation limit = Aggregation.limit(1);
		
		// 동시에 가능
		Aggregation aggregation = Aggregation.newAggregation(match, sort, skip, limit);
		
		// *************************나머지는 패턴
		
		AggregationResults<MongoTestVO> result =
				mongoTemplate.aggregate(aggregation, "person", MongoTestVO.class);
		
		// return type : MongoTestVO
		List<MongoTestVO> dataList = result.getMappedResults();
		for (MongoTestVO mongoTestVO : dataList) {
			System.out.println(mongoTestVO.getName());
			System.out.println(mongoTestVO.getAddress());
			System.out.println(mongoTestVO.getId());
		}
	}
	
	private void pagingData(String key, String value, int pageNo) {
		
		Criteria criteria = new Criteria(key);
		criteria.regex(value); // regular expression
		
		// ************************패턴에서 이부분만 바뀜
		MatchOperation match = Aggregation.match(criteria);		
		
		// 정렬하기
		SortOperation sort = Aggregation.sort(Sort.Direction.DESC, "_id");
		
		// 건너뛰기
		SkipOperation skip = Aggregation.skip( (pageNo - 1 )*10 );
		
		// 지정한 수 만큼 가져오기
		LimitOperation limit = Aggregation.limit(10);
		
		// 동시에 가능
		Aggregation aggregation = Aggregation.newAggregation(match, sort, skip, limit);
		
		// *************************나머지는 패턴
		
		AggregationResults<MongoTestVO> result =
				mongoTemplate.aggregate(aggregation, "person", MongoTestVO.class);
		
		// return type : MongoTestVO
		List<MongoTestVO> dataList = result.getMappedResults();
		for (MongoTestVO mongoTestVO : dataList) {
			System.out.println(mongoTestVO.getName());
			System.out.println(mongoTestVO.getAddress());
			System.out.println(mongoTestVO.getId());
		}
	}
	
	private void groupData() {
		
		Criteria criteria = new Criteria("name");
		criteria.is("도우너");
		MatchOperation match = Aggregation.match(criteria);
		
		// name group의 count를 alias cnt로 보겠다.
		GroupOperation group = Aggregation.group("name").count().as("cnt");
		Aggregation aggregation = Aggregation.newAggregation(match, group);
		
		AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "person", Map.class);
		
		List<Map> list = results.getMappedResults();
		
		for (Map map : list) {
			System.out.println(map);
		}
		
	}
	
	//collection에 person을 적으면 insert안에 person을 적지 않아도 자동으로 들어감
	@Document(collection="person")
	private static class MongoTestVO {
		
		@Id
		private String id;
		
		private String name;
		private String address;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
		
	}
}
