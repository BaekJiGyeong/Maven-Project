package com.ktds.sems.member.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml",
		"/menuContext.xml", "/rootContext.xml" })
public class MemberBizTest {

	@Autowired
	private MemberBiz memberBiz;

	/**
	 * 계정 잠겨있는지 확인
	 * 
	 */
	@Test
	public void isAccountLock() {

		String id = "cocomo";
		boolean isSuccess = !memberBiz.isAccountLock(id);
		assertTrue(isSuccess);
	}

	/**
	 * 로그인
	 * 
	 */
	@Test
	public void login() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpSession session = new MockHttpSession();

		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo");
		memberVO.setPassword("123qwe!@#qwe");

		boolean isSuccess = memberBiz.login(session, memberVO, request);
		assertTrue(isSuccess);
	}

	/**
	 * 로그인 성공 확인
	 * 
	 */
	@Test
	public void loginSuccess() {

		String id = "cocomo";
		boolean isSuccess = memberBiz.loginSuccess(id);
		assertTrue(isSuccess);
	}

	/**
	 * 로그인 실패 횟수 증가
	 * 
	 */
	@Test
	public void plusLoginFailCount() {

		String id = "cocomo";
		boolean isSuccess = memberBiz.plusLoginFailCount(id);
		assertTrue(isSuccess);
	}

	/**
	 * 계정 잠금상태 최신화
	 * 
	 */
	@Test
	public void updateAccountLock() {

		String id = "cocomo";
		boolean isSuccess = !memberBiz.updateAccountLock(id);
		assertTrue(isSuccess);
	}

	/**
	 * 30일이 지나서 비밀번호 바꿔야 하는지 확인
	 * 
	 */
	@Test
	public void needToChangPassword() {

		String id = "cocomo";
		boolean isSuccess = memberBiz.needToChangPassword(id);
		assertTrue(isSuccess);
	}

	/**
	 * 존재하는 아이디 인지 확인
	 * 
	 */
	@Test
	public void isExistId() {
		String id = "cocomo";
		boolean isSuccess = memberBiz.isExistId(id);
		assertTrue(isSuccess);
	}

	/**
	 * 아이디 이용해서 SALT 얻어오기
	 * 
	 */
	@Test
	public void getSaltById() {
		String id = "cocomo";
		String salt = memberBiz.getSaltById(id);
		assertNotNull(salt);
	}

	/**
	 * 탈퇴한 회원인지 확인
	 * 
	 */
	@Test
	public void isResign() {
		String id = "cocomo";
		boolean isSuccess = !memberBiz.isResign(id);
		assertTrue(isSuccess);
	}

}
