package com.ktds.sems.member.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
								  "/applicationContext.xml"
								, "/educationContext.xml"
								, "/memberContext.xml"
								, "/menuContext.xml"
								, "/rootContext.xml"})
public class MemberControllerTest {

	@Autowired
	private MemberController memberController;
	
	@Test
	public void viewGrdtPageTest(){
		ModelAndView view = memberController.viewGrdtPage();
		assertNotNull(view.getViewName());
		
		if ( view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/grdtPage");
			
			List<MemberVO> memberList = (List<MemberVO>) view.getModelMap().get("grtdTpList");
			assertNotNull(memberList);
			assertTrue(memberList.size() > 0);
		}
	}
	
	@Test
	public void deleteGrdtTest(){
		String cdId = "LEAV";
		
		String view = memberController.doGrdtDelete(cdId);
		assertNotNull(view);
		
	}
	
	@Test
	public void viewMbrTpPageTest(){
		ModelAndView view = memberController.viewMbrTpPage();
		assertNotNull(view.getViewName());
		
		if ( view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/mbrTp");
			
			List<MemberVO> memberList = (List<MemberVO>) view.getModelMap().get("mbrTpVOList");
			assertNotNull(memberList);
			assertTrue(memberList.size() > 0);
		}
	}
	
	@Test
	public void doMbrTpDeleteTest(){
		String cdId = "JunitTest";		
		String view = memberController.doMbrTpDelete(cdId);
		assertNotNull(view);		
	}
	

}
