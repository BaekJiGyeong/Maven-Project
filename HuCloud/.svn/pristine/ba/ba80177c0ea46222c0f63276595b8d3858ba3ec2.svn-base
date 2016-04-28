package kr.co.hucloud.security.code.example.attack.sql.injection.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.co.hucloud.security.code.example.member.service.MemberService;
import kr.co.hucloud.security.code.example.member.vo.LoginVO;
import kr.co.hucloud.security.code.example.member.vo.MemberVO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SQLInjectionController {

	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/attack/injection")
	public String sqlInjection() {
		return "attack/sqlInjection/sqlInjection";
	}
	
	@RequestMapping("/attack/injection/attack1")
	public ModelAndView attack1(HttpServletRequest request) {
		LoginVO loginVO = new LoginVO();
		loginVO.setId(request.getParameter("id"));
		loginVO.setPassword(request.getParameter("password"));
		
		boolean isLoginSuccess = memberService.login(request.getSession(), loginVO);
		
		ModelAndView view = new ModelAndView("attack/sqlInjection/sqlInjection");
		view.addObject("result", isLoginSuccess ? "인증성공" : "인증실패");
		view.addObject("loginVO1", loginVO);
		
		return view;
	}
	
	@RequestMapping("/attack/injection/attack2")
	public ModelAndView attack2(HttpServletRequest request) {
		
		List<MemberVO> memberInfo = memberService.getUserInfo(request.getParameter("id"));
		
		ModelAndView view = new ModelAndView("attack/sqlInjection/sqlInjection");
		view.addObject("userId", request.getParameter("id"));
		view.addObject("memberInfo2", memberInfo);
		
		return view;
	}
	
	@RequestMapping("/attack/injection/attack3")
	public ModelAndView attack3(HttpServletRequest request) {
		
		List<MemberVO> memberInfo = memberService.getUserInfo(request.getParameter("id"));
		
		ModelAndView view = new ModelAndView("attack/sqlInjection/sqlInjection");
		view.addObject("userId", request.getParameter("id"));
		view.addObject("memberInfo3", memberInfo);
		
		return view;
	}
	
	@RequestMapping("/attack/injection/commandAttack")
	public ModelAndView commandAttack(HttpServletRequest request) {
		
		String command = request.getParameter("programs");
		//FIXME Command Injection White List 로 변경
		
		try {
			Runtime.getRuntime().exec("cmd.exe /c " + command);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		ModelAndView view = new ModelAndView("attack/sqlInjection/sqlInjection");
		return view;
		
	}
	
	
	
}

