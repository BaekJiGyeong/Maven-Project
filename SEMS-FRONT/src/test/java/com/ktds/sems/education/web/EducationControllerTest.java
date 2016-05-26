package com.ktds.sems.education.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.servlet.http.HttpServletResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.member.vo.MemberVO;

@Transactional
public class EducationControllerTest extends SemsTestCase {

	@Autowired
	private EducationController educationController;

	@Test
	public void viewRequestRetractionPageTest() {
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");
		session.setAttribute(Session.MEMBER, memberVO);
		String educationId = "ED-20160513-000173";
		ModelAndView view = educationController.viewRequestRetractionPage(session, educationId);
		assertNotNull(view);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/retraction");
	}

	@Test
	public void doRequestRetractionActionTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("educationId", "ED-20160513-000173");
		request.setParameter("retractionMessage", "하기싫어요");

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");
		session.setAttribute(Session.MEMBER, memberVO);

		String result = educationController.doRequestRetractionAction(request, session);
		assertNotNull(result);
		assertEquals(result, "redirect:/member/myPage");
	}

	
	@Test
	public void showMyQNAListTest() {

		QNASearchVO qnaSearchVO = new QNASearchVO();
		MockHttpSession session = new MockHttpSession();

		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");

		session.setAttribute(Session.MEMBER, memberVO);

		ModelAndView view = educationController.showMyQNAList(qnaSearchVO, session);

		if (view != null) {

			String viewName = view.getViewName();
			assertNotNull(viewName);

		} else {
			fail("fail");
		}
	}
	
	@Test
	public void showMyQNADetailTest() {
		String replyId = "RP-20160512-000028";
		MockHttpSession session = new MockHttpSession();
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		
		session.setAttribute(Session.MEMBER, memberVO);
		
		ModelAndView view = educationController.showMyQNADetail(replyId, session);
		
		if(view != null) {
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void exportQNAListAsExcelTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER, memberVO);
		String viewName = educationController.exportQNAListAsExcel(session);
		assertNotNull(viewName);
		assertEquals(viewName, "redirect:/myPage/myQNAList");
	}
	
	// @Test
	public void doWriteActionTest() {
		String educationId = "ED-20160510-000011";
		QNAVO qnaVO = new QNAVO();
		qnaVO.setDescription("댓글달자");

		MockHttpSession session = new MockHttpSession();

		MemberVO mockMbr = new MemberVO();
		mockMbr.setId("pleasure57");
		mockMbr.setPassword("11dd0e95242d3decc81eb693abfb25ce2945132b6b127cbd7175670fdf328c71");
		mockMbr.setName("황성재");
		mockMbr.setEmail("pleasure0507@hanmail.net");
		mockMbr.setHighestEducationLevel("UNIV");
		mockMbr.setUniversityName("백석대학교");
		mockMbr.setMajorName("정보통신학부");
		mockMbr.setGraduationType("EXPT");
		mockMbr.setBirthDate("1988-05-07");
		mockMbr.setPhoneNumber("01024410476");
		mockMbr.setMemberType("MBR");
		mockMbr.setSalt("12971a33944e134f");
		mockMbr.setLoginFailCount(0);
		mockMbr.setIsAccountLock("N");
		mockMbr.setLatestLoginDate("16/05/12");
		mockMbr.setResignDate("");
		mockMbr.setIsResign("N");
		mockMbr.setUuid("");
		mockMbr.setModifyFailCount(0);
		mockMbr.setIsModifyLock("N");

		session.setAttribute(Session.MEMBER, mockMbr);

		BindingResult errors = new BeanPropertyBindingResult(qnaVO, "qnaVO");

		ModelAndView view = educationController.doWriteAction(qnaVO, errors, educationId, session);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/eduDetail/" + educationId);
		} else {
			fail("view is null");
		}
	}
	
   //@Test
   public void doCancelEducationTest() {
      String educationId = "ED-20160513-000169";
      
      MockHttpSession session = new MockHttpSession();
      
      String dummy = educationController.doCancelEducation(educationId, session);
      
      assertNotNull(dummy);
   }
   
	
	@Test
	public void doSearchListTest() {
		String startYear = "2010";
		String startMonth = "01";
		String endYear = "2010";
		String endMonth = "02";
		String eduName = "JAVA Test";
		String educationType = "TMMMasdasdasdsdaM";
		String cost = "000";
		String pageNo = "0";

		ModelAndView view = educationController.doSearchList(startYear, startMonth, endYear, endMonth, eduName,
				educationType, cost, pageNo);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/list");
		} else {
			fail("view is null");
		}

	}

	@Test
	public void viewEducationListPageTest() {
		int pageNo = 0;

		ModelAndView view = educationController.viewEducationListPage(pageNo);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/list");
		} else {
			fail("view is null");
		}
	}

	@Test
	public void getOneEducationDetailTest() {
		// FILES에 없는 educationId를 넣으면 에러

		int pageNo = 0;
		String educationId = "ED-20160513-000167";
		MockHttpSession session = new MockHttpSession();

		MemberVO mockMbr = new MemberVO();
		mockMbr.setId("pleasure57");
		mockMbr.setPassword("11dd0e95242d3decc81eb693abfb25ce2945132b6b127cbd7175670fdf328c71");
		mockMbr.setName("황성재");
		mockMbr.setEmail("pleasure0507@hanmail.net");
		mockMbr.setHighestEducationLevel("UNIV");
		mockMbr.setUniversityName("백석대학교");
		mockMbr.setMajorName("정보통신학부");
		mockMbr.setGraduationType("EXPT");
		mockMbr.setBirthDate("1988-05-07");
		mockMbr.setPhoneNumber("01024410476");
		mockMbr.setMemberType("MBR");
		mockMbr.setSalt("12971a33944e134f");
		mockMbr.setLoginFailCount(0);
		mockMbr.setIsAccountLock("N");
		mockMbr.setLatestLoginDate("16/05/12");
		mockMbr.setResignDate("");
		mockMbr.setIsResign("N");
		mockMbr.setUuid("");
		mockMbr.setModifyFailCount(0);
		mockMbr.setIsModifyLock("N");

		session.setAttribute(Session.MEMBER, mockMbr);

		ModelAndView view = educationController.getOneEducationDetail(educationId, session, pageNo);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduDetail");
		} else {
			fail("view is null");
		}

	}
	
   @Test
   public void viewEducationCalendarPageTest(){
      ModelAndView view = educationController.viewEducationCalendarPage();
      assertNotNull(view);
   }
   
   
   //@Test
   public void doDownloadFileTest(){
      String educationId = "ED-20160513-000171";
      MockHttpServletResponse response = new MockHttpServletResponse();
      MockHttpServletRequest request = new MockHttpServletRequest();
      
      educationController.doDownloadFile(educationId, request, response);
      /*
      if (view != null) {
         String viewName = view.getViewName();
         assertNotNull(viewName);
         assertEquals(viewName, "education/list");
      } else {
         fail("view is null");
      }*/
   }

	
	
}
