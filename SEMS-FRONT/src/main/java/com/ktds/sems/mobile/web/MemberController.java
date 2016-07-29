package com.ktds.sems.mobile.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationListVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.service.impl.MemberServiceImpl;
import com.ktds.sems.member.vo.AttendListVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class MemberController {
	
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/m/login")
	@ResponseBody
	public Map<String, String> login(@Valid MemberVO memberVO, Errors errors, HttpSession session, HttpServletRequest request) {
		
		String loginStatus = memberService.login(memberVO, errors, session, request);
		
		/**
		 * {
		 * 	result : true,
		 * 	message : " 성공했습니다. " 
		 * }
		 * 
		 * {
		 * 	result : false, 
		 * 	message : " 이미 탈퇴한 회원 아이디입니다. "
		 */
		
		Map<String , String> result = new HashMap<String, String>();
		
		boolean loginResult = loginStatus.equals(MemberServiceImpl.VALID_ID);
		result.put("result", loginResult+"");
		
		if(loginStatus.equals(MemberServiceImpl.VALID_ID)){
			result.put("message", "로그인을 성공했습니다.");
			result.put("sessionId", session.getId()+"");
		}
		else if ( loginStatus.equals(MemberServiceImpl.NOT_EXISTS_ID)){
			result.put("message", "ID혹은 Password를 올바르게 입력하세요");
		}
		else if ( loginStatus.equals(MemberServiceImpl.WAS_BLOCK_ID)){
			result.put("message", "관리자에게 문의하세요");
		}
		else if ( loginStatus.equals(MemberServiceImpl.WAS_RESIGN_ID)){
			result.put("message", "탈퇴한 회원입니다.");
		}
		else if ( loginStatus.equals(MemberServiceImpl.CHANGE_PASSWORD)){
			result.put("message", "비밀번호를 변경하세요");
		}
		return result;
	}
	
	@RequestMapping("/m/myCourse")
	@ResponseBody
	public Map<String, List<EducationVO>> getMyCourse(HttpSession session){
		
		ModelAndView view = memberService.getCourseList(session,  0,  new EducationSearchVO());
		List<EducationVO> myEducationList = (List<EducationVO>) view.getModelMap().get("educationListVO");
		
		List<EducationVO> myPreEducationList = null;
		EducationListVO preEducationListVO = (EducationListVO) view.getModelMap().get("preEducationListVO");
		
		myPreEducationList = preEducationListVO.getEducationList();
		
		Map<String, List<EducationVO>> result = new HashMap<String, List<EducationVO>>();
		result.put("nowEduList", myEducationList);
		result.put("preEduList", myPreEducationList);
		
		return result;
	}
	
	@RequestMapping("/m/getAttendList/{educationId}")
	@ResponseBody
	public EducationAttendVO getAttendList(HttpSession session, @PathVariable String educationId){
		
		EducationVO eduInfo = null;
		Map<String,List<String>> attends = new HashMap<String,List<String>>();
		
		
		AttendListVO attendList = null;
		ModelAndView view = null;
		
		for(int i=0; i<3; i++){
			view = memberService.getAllAttendHistory(session, educationId, i);
			if(i==0){
				eduInfo = (EducationVO) view.getModelMap().get("eduInfo");
			}
			attendList = (AttendListVO) view.getModel().get("attendList");
			if(attendList.getAttends() != null){
				attends.putAll(attendList.getAttends());
			}
		}
		
		EducationAttendVO result = new EducationAttendVO();
		result.setEduInfo(eduInfo);
		result.setAttends(attends);

		
		return result;
	}
	
	@RequestMapping("/m/logout")
	@ResponseBody
	public Map<String, String> logout(HttpSession session){
		session.invalidate();
		
		Map<String, String> result= new HashMap<String, String>();
		result.put("result", "true");
		
		return result;
	}
	
	@RequestMapping("/m/leave")
	@ResponseBody
	public void doLeaveClassAction(HttpServletResponse response, HttpSession session){
		memberService.updateLeaveClass(response, session);
	}
	
	@RequestMapping("/m/checkAttend")
	public void checkAttend(@RequestParam String id, HttpServletResponse response){
		
		boolean doesAttend = memberService.checkAttend(id);
		
		AjaxUtil.sendResponse(response, doesAttend+"");
	}
	
}
