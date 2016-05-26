package com.ktds.sems.team.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;

@Transactional
public class TeamDAOTest extends SemsTestCase {

	@Autowired
	private TeamDAO teamDAO;
	
	@Test
	public void getAllTeamListTest() {
		TeamSearchVO searchVO = new TeamSearchVO();
		
		searchVO.setSearchKeyword("");
		searchVO.setSearchType("");
		searchVO.setStartIndex(0);
		searchVO.setEndIndex(10);
		
		List<TeamVO> teamList = teamDAO.getAllTeamList(searchVO);
		assertNotNull(teamList);
		if (teamList != null ) {
			assertTrue(teamList.size() >0 );
		}
		else {
			fail("Fail...");
		}
	}
	
}
