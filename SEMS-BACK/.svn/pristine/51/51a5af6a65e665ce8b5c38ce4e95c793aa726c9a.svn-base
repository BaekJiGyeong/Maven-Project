package com.ktds.sems.pc.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;

@Transactional
public class PcServiceTest extends SemsTestCase {
	
	@Autowired
	private PcService pcService;
	
	@Test
	public void getUsedPcListTest() {
		
		UsedPcSearchVO usedPcSearchVO = new UsedPcSearchVO();
		usedPcSearchVO.setPageNo(0);
		usedPcSearchVO.setSearchType("ip");
		usedPcSearchVO.setSearchKeyword("0");
		
		ModelAndView view = pcService.getUsedPcList(usedPcSearchVO);
		
		if(view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "pc/usedPcList");
		} else {
			fail("fail");
		}
	}

	@Test
	public void getReportedPcListWithPagingTest() {
		
		ReportedPcSearchVO reportedPcSearchVO = new ReportedPcSearchVO();
		reportedPcSearchVO.setPageNo(0);
		reportedPcSearchVO.setSearchType("pcName");
		reportedPcSearchVO.setSearchKeyword("0");
		
		ModelAndView view = pcService.getReportedPcListWithPaging(reportedPcSearchVO);
		
		if(view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "pc/reportedPcList");
		} else {
			fail("fail");
		}
	}

	@Test
	public void changeReportedStateTest() {
		ReportedPcVO reportedPcVO = new ReportedPcVO();
		reportedPcVO.setReportedPcId("RP-20160524-000011");
		reportedPcVO.setReportedState("PC_PB_CH");
		
		String data = pcService.changeReportedState(reportedPcVO);
		assertNotNull(data);
	}

	
}
