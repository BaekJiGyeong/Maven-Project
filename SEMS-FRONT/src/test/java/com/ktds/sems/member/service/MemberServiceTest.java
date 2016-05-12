package com.ktds.sems.member.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.ktds.sems.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml",
		"/menuContext.xml", "/rootContext.xml" })
public class MemberServiceTest {

	@Autowired
	private MemberService memberService;

	/**
	 * 로그인
	 */
	@Test
	public void login() {

		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo");
		memberVO.setPassword("123qwe!@#qwe");
		
		BindingResult errors = new BeanPropertyBindingResult(memberVO, "loginForm");
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpSession session = new MockHttpSession();
		
		String checkStr = memberService.login(memberVO, errors, session, request);
		assertNotNull(checkStr);
	}
}
