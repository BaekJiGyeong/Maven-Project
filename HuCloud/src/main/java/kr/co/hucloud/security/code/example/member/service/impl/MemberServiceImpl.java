package kr.co.hucloud.security.code.example.member.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.hucloud.security.code.example.common.LoginStore;
import kr.co.hucloud.security.code.example.common.Session;
import kr.co.hucloud.security.code.example.member.dao.MemberDAO;
import kr.co.hucloud.security.code.example.member.service.MemberService;
import kr.co.hucloud.security.code.example.member.vo.LoginVO;
import kr.co.hucloud.security.code.example.member.vo.MemberRegistryVO;
import kr.co.hucloud.security.code.example.member.vo.MemberVO;
import kr.co.hucloud.utilities.SHA256Util;

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
		String memberSalt = memberDAO.getSaltById( loginVO.getId());
		String inputPassword = loginVO.getPassword();
		String newPassword = SHA256Util.getEncrypt(inputPassword, memberSalt);
		
		MemberVO memberVO = memberDAO.login(loginVO);
		
		if(memberVO != null) {
			
			// 로그인 성공시..
			LoginStore loginStore = LoginStore.getInstance();
			if ( loginStore.get(loginVO.getId())!= null ){
				loginStore.logout(loginVO.getId());
			}
			session.setAttribute(Session.MEMBER, memberVO);
			loginStore.add(loginVO.getId(), session);
			
			session.setAttribute(Session.MEMBER, memberVO);
		}
		
		return memberVO != null;
	}
	
	@Override
	public List<MemberVO> getUserInfo(String parameter) {
		return memberDAO.getUserInfo(parameter);
	}

	@Override
	public void loginSuccess(String id) {
		memberDAO.loginSuccess(id);
		
	}

	@Override
	public void plusLoginFailCount(String id) {
		memberDAO.plusLoginFailCount(id);
	}

	@Override
	public void updateAccountLock(String id) {
		memberDAO.updateAccountLock(id);
	}

	@Override
	public boolean isAccountLock(String id) {
		return memberDAO.isAccountLock(id);
	}
}
