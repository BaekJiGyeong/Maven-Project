package com.ktds.jgbaek.article.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.jgbaek.article.biz.impl.ArticleBizImpl;
import com.ktds.jgbaek.article.vo.ArticleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/articleContext.xml", "/rootContext.xml" })
public class ArticleBizImplTest {
	
	@Autowired
	private ArticleBizImpl articleBizImpl;

	@Test
	public void writeNewArticleTest() {
		ArticleVO articleVO = new ArticleVO();
		Boolean bizTest = articleBizImpl.writeNewArticle(articleVO);
		assertNotNull(bizTest);
		if (bizTest != null) {
		
		}
		else {
			fail("view is null");
		}
		
		fail("Not yet implemented");
	}

}
