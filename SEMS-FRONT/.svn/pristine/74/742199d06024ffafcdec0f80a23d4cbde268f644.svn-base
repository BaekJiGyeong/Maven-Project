package com.ktds.sems.team.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.team.vo.TeamSearchVO;

@Transactional
public class TeamServiceTest extends SemsTestCase {

	@Autowired
	private TeamService teamService;
	
	
	@Test
	public void getAllTeamListPageTest() {
		
		TeamSearchVO teamSearchVO = new TeamSearchVO();
		teamSearchVO.setPageNo(0);
		teamSearchVO.setSearchType("teamId");
		teamSearchVO.setSearchKeyword("0");

		ModelAndView view = teamService.getAllTeamListPage(teamSearchVO);

		if (view != null) {

			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "team/teamList");

		} else {
			fail("fail");
		}
	}

}
