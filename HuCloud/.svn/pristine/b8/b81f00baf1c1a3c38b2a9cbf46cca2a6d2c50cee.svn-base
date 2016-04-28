package kr.co.hucloud.security.code.example.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.hucloud.security.code.example.member.vo.LoginVO;
import kr.co.hucloud.security.code.example.member.vo.MemberRegistryVO;
import kr.co.hucloud.security.code.example.member.vo.MemberVO;

public interface MemberService {

	public void addMember(MemberRegistryVO memberVO);

	public boolean login(HttpSession session, LoginVO loginVO);

	public List<MemberVO> getUserInfo(String parameter);
	
}
