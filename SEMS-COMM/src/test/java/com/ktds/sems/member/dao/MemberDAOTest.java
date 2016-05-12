package com.ktds.sems.member.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml",
		"/menuContext.xml", "/rootContext.xml" })
public class MemberDAOTest {

	@Autowired
	private MemberDAO memberDAO;

	/**
	 * SALT 얻어오기
	 */
	@Test
	public void getSaltById() {
		String id = "cocomo";
		String salt = memberDAO.getSaltById(id);
		assertNotNull(salt);
	}

	/**
	 * 로그인
	 */
	@Test
	public void login() {
		MemberVO loginVO = new MemberVO();
		loginVO.setId("cocomo");
		loginVO.setPassword("123qwe!@#qwe");
		
		MemberVO memberVO = memberDAO.login(loginVO);
		if(memberVO != null) {
			assertNotNull(memberVO.getId());
			assertNotNull(memberVO.getPassword());
			assertNotNull(memberVO.getName());
			assertNotNull(memberVO.getEmail());
			assertNotNull(memberVO.getBirthDate());
			assertNotNull(memberVO.getPhoneNumber());
			assertNotNull(memberVO.getMemberType());
			assertNotNull(memberVO.getSalt());
			assertNotNull(memberVO.getLoginFailCount());
			assertNotNull(memberVO.getIsResign());
			assertNotNull(memberVO.getModifyFailCount());
			assertNotNull(memberVO.getIsModifyLock());
		} else {
			fail("fail");
		}
	}

	@Test
	public void isAccountLock() {
		String id = "cocomo";
		boolean isSuccess = memberDAO.isAccountLock(id);
		assertTrue(!isSuccess);
	}

	@Test
	public void loginSuccess() {
		String id = "cocomo";
		int executeQuery = memberDAO.loginSuccess(id);
		assertTrue(executeQuery > 0);
	}

	@Test
	public void plusLoginFailCount() {
		String id = "cocomo";
		int executeQuery = memberDAO.plusLoginFailCount(id);
		assertTrue(executeQuery > 0);
	}

	@Test
	public void updateAccountLock() {
		String id = "cocomo";
		int executeQuery = memberDAO.updateAccountLock(id);
		assertTrue(executeQuery > 0);
	}

	@Test
	public void needToChangPassword() {
		String id = "cocomo";
		String checkStr = memberDAO.needToChangPassword(id);
		assertNull(checkStr);
	}

	@Test
	public void isExistId() {
		String id = "cocomo";
		String checkStr = memberDAO.isExistId(id);
		assertNotNull(checkStr);
	}

	@Test
	public void isResign() {
		String id = "cocomo";
		String checkStr = memberDAO.isResign(id);
		assertNull(checkStr);
	}
	
	@Test
	public void getAllGrdtListTest(){
		List<GrdtTpVO> grdtTpList = memberDAO.getAllGrtdList();
		assertNotNull(grdtTpList);
		assertTrue(grdtTpList.size() >= 0);
	}
	
	@Test
	public void doGrdtInsertTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("TEST");
		grdtTpVO.setCdNm("JUNITTEST");
		
		int checkGrdtInsert = memberDAO.doGrdtInsert(grdtTpVO);
		assertTrue(checkGrdtInsert >= 1);
	}
	
	@Test
	public void doGrdtModifyTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("TEST");
		grdtTpVO.setCdNm("JUNITTEST");
		
		int checkGrdtModify = memberDAO.doGrdtModify(grdtTpVO);
		assertTrue(checkGrdtModify >= 1);
	}
	
	@Test
	public void doGrdtDeleteTest(){
		String cdId = "TEST";
		int checGrdtkDelete = memberDAO.doGrdtDelete(cdId);
		assertTrue(checGrdtkDelete >= 1);
	}
	
	@Test
	public void isExistDataTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("EXPT");
		grdtTpVO.setCdNm("졸업");
		
		int checkExistData = memberDAO.isExistData(grdtTpVO);
		assertTrue(checkExistData >= 1);
	}
	
	@Test
	public void isExistCdNmDataTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdNm("졸업예정");
		
		int checkExistCdNmData = memberDAO.isExistCdNmData(grdtTpVO);
		assertTrue(checkExistCdNmData >= 1);
	}
	
	///
	
	@Test
	public void getAllMbrTpListTest(){
		List<MbrTpVO> mbrTpList = memberDAO.getAllMbrTpList();
		assertNotNull(mbrTpList);
		assertTrue(mbrTpList.size() >= 0);
	}
	
	@Test
	public void doInsertMbrTpTest(){
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId("TEST");
		mbrTpVO.setCdNm("JUNITTEST");
		
		int checkMbrTpInsert = memberDAO.doInsertMbrTp(mbrTpVO);
		assertTrue(checkMbrTpInsert >= 1);
	}
	
	@Test
	public void doMbrTpModifyTest(){
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId("TEST");
		mbrTpVO.setCdNm("JUNITTEST");
		
		int checkMbrTpModify = memberDAO.doMbrTpModify(mbrTpVO);
		assertTrue(checkMbrTpModify >= 1);
	}
	
	@Test
	public void doMbrTpDeleteTest(){
		String cdId = "TEST";
		int checkMbrTpDelete = memberDAO.doMbrTpDelete(cdId);
		assertTrue(checkMbrTpDelete >= 1);
	}
	
	@Test
	public void isExistMbrTpDataTest(){
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId("TEST");
		mbrTpVO.setCdNm("JUNITTEST");
		
		int checkExistMbrTpData = memberDAO.isExistMbrTpData(mbrTpVO);
		assertTrue(checkExistMbrTpData >= 1);
	}
	
	///
	
}
