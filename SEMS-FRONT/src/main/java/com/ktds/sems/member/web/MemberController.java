package com.ktds.sems.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class MemberController {

	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping(value = "/doRegisterAction", method = RequestMethod.POST)
	public ModelAndView registerNewMember(@Valid MemberVO member, Errors errors, HttpSession session) {
		return memberService.addNewMember(member, errors, session);
	}

	@RequestMapping("/main")
	public String viewMainPage() {
		return "/common/main";
	}

	@RequestMapping("/changePassword")
	public String viewChangePasswordPage() {
		return "/member/changePassword";
	}

	@RequestMapping("/register/policy")
	public String viewRegisterPage() {
		return "member/registerPolicy";
	}

	@RequestMapping("/register/student")
	public ModelAndView viewRegisterStudentPage() {
		return memberService.registerStudent();
	}

	@RequestMapping("/register/teacher")
	public String viewRegisterTeacherPage() {
		return "member/registerTeacher";
	}

	@RequestMapping("/checkValidationById")
	public void checkValidationById(@RequestParam String id, HttpServletResponse response) {
		memberService.checkValidationById(id, response);
	}

	@RequestMapping("/checkValidationByPassword")
	public void checkValidationByPassword(@RequestParam String password, HttpServletResponse response) {
		memberService.checkValidationByPassword(password, response);
	}

	@RequestMapping("/checkValidationByRepeatPassword")
	public void checkValidationByRepeatPassword(@RequestParam String password, @RequestParam String repeatPassword,
			HttpServletResponse response) {
		memberService.checkValidationByRepeatPassword(password, repeatPassword, response);
	}

	@RequestMapping("/checkExistionByEmail")
	public void checkExistionByEmail(@RequestParam String email, HttpServletResponse response) {
		memberService.checkExistionByEmail(email, response);
	}

	@RequestMapping(value = ("/login"), method = RequestMethod.POST)
	public void login(@Valid MemberVO memberVO, Errors errors, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) {
		String loginStatus = memberService.login(memberVO, errors, session, request);
		AjaxUtil.sendResponse(response, loginStatus);
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		memberService.logout(session);
		return "redirect:/";
	}

	@RequestMapping("/member/myPage")
	public ModelAndView viewMyPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("member/myPage");
		return view;
	}

	@RequestMapping("/member/myPage/checkPassword")
	public ModelAndView viewCheckPasswordPage() {

		ModelAndView view = new ModelAndView();
		view.setViewName("member/checkPassword");
		return view;
	}

	@RequestMapping("/member/myPage/doCheckPassword")
	public ModelAndView doCheckPassword(@RequestParam String password, HttpSession session,
			HttpServletResponse response) {

		ModelAndView view = new ModelAndView();

		// TODO 입력한 패스워드와
		// 테이블의 member.getPassword() 혹은 세션패스워드 를 해독했을때의 암호가
		// 일치한다면 SendMessage.send(response, "OK");

		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		String sessionId = member.getId();
		String originSalt = memberService.getSaltById(sessionId);
		String inputPassword = SHA256Util.getEncrypt(password, originSalt);

		String originPassword = memberService.getPasswordById(sessionId);
		if (inputPassword.equals(originPassword)) {
			/*
			 * 1. MODIFY_FAIL_COUNT를 0 으로 초기화한다. 2. IS_MODIFY_ACCOUNT_LOCK을
			 * 'N'으로 초기화한다.
			 */
			memberService.resetModifyLockAndCount(sessionId);

			AjaxUtil.sendResponse(response, "OK");

			memberService.modifySuccess(sessionId);
			return null;
		} else {

			/*
			 * 1. MODIFY_FAIL_COUNT 를 1 증가시킨다.
			 * 
			 */
			memberService.plusModifyFailCount(sessionId);

			memberService.updateModifyAccountLock(sessionId);

			/*
			 * 1. MODIFY_FAIL_COUNT 가 3 이상이라면 IS_MODIFY_ACCOUNT_LOCK 'Y'로 수정한다.
			 */
			memberService.updateModifyAccountLock(sessionId);
			/*
			 * 
			 * 1. IS_ACCOUNT_LOCK이 'Y'라면 사용자의 이메일로 비밀번호가 3회 이상 틀려 접속이 차단되었음을
			 * 알린다.
			 */
			boolean isLock = memberService.isModifyAccountLock(sessionId);

			/*
			 * 2. 메일을 보낸다.
			 */
			// FIXME 이메일 테스트 시 주석처리 삭제
			// if( isLock ){
			// memberService.sendBlockAccountEmail(sessionId);
			// }

			/*
			 * 3. IS_ACCOUNT_LOCK이 'Y'라면 브라우저에게 'OVER' 라고 보낸다.
			 */
			AjaxUtil.sendResponse(response, isLock ? "OVER" : "NO");

			view.setViewName("/member/checkPassword");
		}

		return view;
	}

	@RequestMapping("/member/myPage/modify")
	public ModelAndView viewModifyPage(HttpSession session) {

		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");

		String sessionId = member.getId();
		return memberService.modifySuccess(sessionId);

	}

	//
	// @RequestMapping("/member/resign")
	// public String viewResignPage() {
	// return "redirect:member/resign";
	// }

	// @RequestMapping("/member/myPage/sendResignMail")
	// public ModelAndView sendResignMail(@Valid MemberVO memberVO, HttpSession
	// session) {
	// return memberService.sendResignMail(memberVO, session);
	// }
	//
	// @RequestMapping("/member/myPage/{resignCode}")
	// public ModelAndView loginForResign(@PathVariable String resignCode,
	// HttpSession session, MemberVO memberVO){
	// return memberService.loginForResign(resignCode, session, memberVO);
	// }

	@RequestMapping("/member/myPage/doModifyAction")
	public ModelAndView doModifyAction(@Valid MemberVO member, Errors errors, @RequestParam String graduationType,
			@RequestParam String helCodeName) {
		return memberService.modifyMemberInfo(member, errors, graduationType, helCodeName);
	}

	/**
	 * @author 이기연
	 * @param session
	 */
	@RequestMapping("/member/myPage/saveAsExcel")
	public void saveLoginHistoryAsExcel(HttpSession session) {
		memberService.saveLoginHistoryAsExcel(session);
	}

	@RequestMapping("/member/loginHistory")
	public ModelAndView viewLoginHistoryPage(@RequestParam(required = false, defaultValue = "0") int pageNo, HttpSession session) {
		return memberService.viewLoginHistoryPage(pageNo, session);
	}
}
