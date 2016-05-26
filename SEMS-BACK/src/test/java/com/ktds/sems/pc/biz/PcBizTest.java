package com.ktds.sems.pc.biz;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;
import com.ktds.sems.pc.vo.UsedPcVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class PcBizTest extends SemsTestCase {

	@Autowired
	private PcBiz pcBiz;

	@Test
	public void getUsedPcListTest() {
		UsedPcSearchVO usedPcSearchVO = new UsedPcSearchVO();
		usedPcSearchVO.setPageNo(0);
		usedPcSearchVO.setSearchType("ip");
		usedPcSearchVO.setSearchKeyword("0");
		
		Paging paging = new Paging();
		paging.setPageNumber(usedPcSearchVO.getPageNo() + "");
		paging.setTotalArticleCount(pcBiz.getTotalUsedPcCount(usedPcSearchVO));
		
		usedPcSearchVO.setStartIndex(paging.getStartArticleNumber());
		usedPcSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<UsedPcVO> usedPcList = pcBiz.getUsedPcList(usedPcSearchVO);
		if(usedPcList != null) {
			
			for (UsedPcVO usedPcVO : usedPcList) {
				assertNotNull(usedPcVO.getUsedPcId());
			}
			
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
		
		Paging paging = new Paging();
		paging.setPageNumber(reportedPcSearchVO.getPageNo() + "");
		paging.setTotalArticleCount(pcBiz.getTotalReportedPcCount(reportedPcSearchVO));
		
		reportedPcSearchVO.setStartIndex(paging.getStartArticleNumber());
		reportedPcSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<ReportedPcVO> reportedPcList = pcBiz.getReportedPcListWithPaging(reportedPcSearchVO);
		if(reportedPcList != null) {
			
			for (ReportedPcVO reportedPcVO : reportedPcList) {
				assertNotNull(reportedPcVO.getReportedPcId());
			}
			
		} else {
			fail("fail");
		}
	}

	@Test
	public void changeReportedStateTest() {
		ReportedPcVO reportedPcVO = new ReportedPcVO();
		reportedPcVO.setReportedPcId("RP-20160524-000011");
		reportedPcVO.setReportedState("PC_PB_CH");
		
		boolean isSuccess = pcBiz.changeReportedState(reportedPcVO);
		assertTrue(isSuccess);
	}

}
