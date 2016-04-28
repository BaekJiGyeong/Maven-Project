package kr.co.hucloud.security.code.example.member.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.hucloud.security.code.example.common.Session;
import kr.co.hucloud.security.code.example.member.dao.MemberDAO;
import kr.co.hucloud.security.code.example.member.service.MemberService;
import kr.co.hucloud.security.code.example.member.vo.LoginVO;
import kr.co.hucloud.security.code.example.member.vo.MemberRegistryVO;
import kr.co.hucloud.security.code.example.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public void addMember(MemberRegistryVO memberVO) {
		memberDAO.addMember(memberVO);
	}
	
	@Override
	public boolean login(HttpSession session, LoginVO loginVO) {
		
		MemberVO memberVO = memberDAO.login(loginVO);
		
		if(memberVO != null) {
			session.setAttribute(Session.MEMBER, memberVO);
		}
		
		return memberVO != null;
	}
	
	@Override
	public List<MemberVO> getUserInfo(String parameter) {
		return memberDAO.getUserInfo(parameter);
	}
}
