package com.ktds.jgbaek.mongo.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class MongoTest {
	
	private MongoTemplate mongoTemplate;
	
	public MongoTest() {
		
		String mongoContextPath = "/mongoContext.xml";
		
		AbstractApplicationContext ctx = 
				new ClassPathXmlApplicationContext(mongoContextPath);
		
		mongoTemplate = (MongoTemplate) ctx.getBean("mongoTemplate");
	}
	
	public static void main(String[] args) {
		MongoTest mongoTest = new MongoTest();
		
		System.out.println(mongoTest.mongoTemplate);
//		mongoTest.insertTestData();
//		mongoTest.insertTestData();
//		mongoTest.insertTestData();
//		mongoTest.insertTestData();
//		mongoTest.insertTestData();
//		
//		MongoTestVO mongoTestVO = new MongoTestVO();
//		mongoTestVO.setName("도우너");
//		mongoTestVO.setAddress("깐따삐야");
//		
//		mongoTest.updateDatas("name", "둘리", mongoTestVO);
//		mongoTest.updateData("name", "도우너", null);
		mongoTest.insertAllTestData();
//		mongoTest.removeAllDatas();
//		mongoTest.removeData("name", "둘리");
		
//		mongoTest.findData("name", "마이콜");
		mongoTest.findDatas("name", "둘리0");
		
	}
	
	private void insertTestData() {
		MongoTestVO testVO = new MongoTestVO();
		testVO.setName("둘리");
		testVO.setAddress("고길동 집 1억년전 어딘가..");
		
		mongoTemplate.insert(testVO);
	}

	private void insertAllTestData() {
		List<MongoTestVO> testVOList = new ArrayList<MongoTestVO>();
		MongoTestVO testVO = null;
		
		for (int i = 0; i < 1000; i++) {
			testVO = new MongoTestVO();
			testVO.setName("둘리"+i);
			testVO.setAddress("고길동 집"+i+"번지");
			testVOList.add(testVO);
		}
		mongoTemplate.insertAll(testVOList);
	}
	
	private void removeAllDatas() {
		// 전체 삭제
		mongoTemplate.remove(new Query(), "person");
	}
	
	private void removeData(String key, String value) {
		Criteria criteria = new Criteria(key);
		criteria.is(value);
		
		// address가 지구인 것도 지워라
		criteria = criteria.and("address");
		criteria.is("지구");
		
		Query query = new Query(criteria);
		
		mongoTemplate.remove(query, "person");
	}
	
	private void updateDatas(String key, String value, MongoTestVO mongoTestVO) {
		
		Criteria criteria = new Criteria(key);
		criteria.is(value);
		Query query = new Query(criteria);
		
		Update update = new Update();
		update.set("name", mongoTestVO.getName());
		update.set("address", mongoTestVO.getAddress());
		
		mongoTemplate.updateMulti(query, update, "person");
		
	}
	
	private void updateData(String key, String value, MongoTestVO mongoTestVO ){
		
		Criteria criteria = new Criteria(key);
		criteria.is(value);
		Query query = new Query(criteria);
		
		Update update = new Update();
		update.set("name", "마이콜");
		update.set("address", "후루룩 짭짭 후루룩 짭짭 맛좋은 라면");
		
		mongoTemplate.updateFirst(query, update, "person");
	}
	
	private void findData(String key, String value) {
		Criteria criteria = new Criteria(key);
		criteria.is(value);
		
		Query query = new Query(criteria);
		
		MongoTestVO mongoTest = mongoTemplate.findOne(query, MongoTestVO.class, "person");
		System.out.println(mongoTest.getName());
		System.out.println(mongoTest.getAddress());
		System.out.println(mongoTest.getId());
	}
	
	private void findDatas(String key, String value) {
		Criteria criteria = new Criteria(key);
//		criteria.is(value);
		criteria.regex("^둘리"); //둘리로 시작하는 데이터
		
		Query query = new Query(criteria);
		
		List<MongoTestVO> datas = mongoTemplate.find(query, MongoTestVO.class, "person");
		
		for (MongoTestVO mongoTestVO : datas) {
			System.out.println(mongoTestVO.getName());
			System.out.println(mongoTestVO.getAddress());
			System.out.println(mongoTestVO.getId());
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
