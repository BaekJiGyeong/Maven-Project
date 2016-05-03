package com.ktds.jgbaek.article.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.jgbaek.article.vo.ArticleListVO;
import com.ktds.jgbaek.article.vo.ArticleVO;

import kr.co.hucloud.utilities.web.Paging;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/articleContext.xml", "/rootContext.xml" })
public class ArticleControllerTest {

	@Autowired
	private ArticleController articleController;

	@Test
	public void viewWritePageTest() {
		ModelAndView view = articleController.viewWritePage();
		assertNotNull(view);
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "article/write");
		}
		else {
			fail("view is null");
		}
	}

	@Test
	public void doWriteActionTest() {
		ModelAndView listView = articleController.viewListPage(0);
		ArticleListVO articleListVO = (ArticleListVO) listView.getModelMap().get("articleListVO");
		int prevTotalCount = articleListVO.getPaging().getTotalArticleCount();
		ArticleVO articleVO = new ArticleVO();
		articleVO.setSubject("JUNIT Test...");
		articleVO.setWriter("JUNIT...");
		articleVO.setDescription("JUNIT Test...Dscription...");

		BindingResult errors = new BeanPropertyBindingResult(articleVO, "writeForm");

		ModelAndView view = articleController.doWriteAction(articleVO, errors);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertEquals(viewName, "redirect:/list");

			listView = articleController.viewListPage(0);
			articleListVO = (ArticleListVO) listView.getModelMap().get("articleListVO");

			int nextTotalCount = articleListVO.getPaging().getTotalArticleCount();

			assertTrue(nextTotalCount - prevTotalCount == 1);

		} else {
			fail("Fail...");
		}
	}

	@Test
	   public void doWriteActionTestWithError() {
	      ArticleVO articleVO = new ArticleVO();
	      articleVO.setWriter("JUNIT Wrtier test");
	      articleVO.setDescription("JUNIT Description test");
	      
	      BindingResult errors = new BeanPropertyBindingResult(articleVO, "writeForm");
	      ArticleVOValidator validator = new ArticleVOValidator();
	      validator.validate(articleVO, errors);
	      
	      ModelAndView view = articleController.doWriteAction(articleVO, errors);
	      assertNotNull(view);
	      
	      if ( view != null ) {
	         String viewName = view.getViewName();
	         assertNotNull(viewName);
	         assertEquals(viewName, "article/write");
	         
	         assertTrue(errors.getErrorCount() == 1);
	      }
	      else {
	         fail("fail !");
	      }
	      
	   }

	@Test
	   public void doWriteActionTestWithError2() {
	      ArticleVO articleVO = new ArticleVO();
	      articleVO.setWriter("JUNIT Wrtier test");
	      articleVO.setSubject("JUNIT Subjec test");
	      
	      BindingResult errors = new BeanPropertyBindingResult(articleVO, "writeForm");
	      ArticleVOValidator validator = new ArticleVOValidator();
	      validator.validate(articleVO, errors);
	      
	      ModelAndView view = articleController.doWriteAction(articleVO, errors);
	      assertNotNull(view);
	      
	      if ( view != null ) {
	         String viewName = view.getViewName();
	         assertNotNull(viewName);
	         assertEquals(viewName, "article/write");
	         
	         assertTrue(errors.getErrorCount() == 1);
	      }
	      else {
	         fail("fail !");
	      }
	      
	   }
	
	@Test
	   public void doWriteActionTestWithError3() {
	      ArticleVO articleVO = new ArticleVO();
	      articleVO.setWriter("JUNIT Wrtier test");
	      
	      BindingResult errors = new BeanPropertyBindingResult(articleVO, "writeForm");
	      ArticleVOValidator validator = new ArticleVOValidator();
	      validator.validate(articleVO, errors);
	      
	      ModelAndView view = articleController.doWriteAction(articleVO, errors);
	      assertNotNull(view);
	      
	      if ( view != null ) {
	         String viewName = view.getViewName();
	         assertNotNull(viewName);
	         assertEquals(viewName, "article/write");
	         
	         assertTrue(errors.getErrorCount() == 2);
	      }
	      else {
	         fail("fail !");
	      }
	      
	   }
	
	@Test
	public void viewListPageTest() {
		ModelAndView view = articleController.viewListPage(0);
		assertNotNull(view);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "article/list");
			
			ArticleListVO articleList = (ArticleListVO) view.getModelMap().get("articleListVO");
			assertNotNull(articleList);
			
			List<ArticleVO> articles = articleList.getArticleList();
			assertNotNull(articles);
			assertTrue(articles.size() > 0);
			
			Paging paging = articleList.getPaging();
			assertNotNull(paging);
			assertTrue(paging.getTotalArticleCount()>0);
		}
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("pageNO", "0");
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		MockHttpSession session = new MockHttpSession();
	}
	
	public class ArticleVOValidator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return ArticleVO.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			ArticleVO articleVO = (ArticleVO) target;

			String subject = articleVO.getSubject();
			if (subject == null || subject.length() == 0) {
				errors.rejectValue("subject", "field.required", "error default message");
			}

			String description = articleVO.getDescription();
			if (description == null || description.length() == 0) {
				errors.rejectValue("description", "field.required", "error default message");
			}
		}
	}
}
