package com.ktds.sems.education.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EduFileListVO;
import com.ktds.sems.education.vo.EduFileSearchVO;
import com.ktds.sems.education.vo.EduFileVO;
import com.ktds.sems.education.vo.EduNoticeListVO;
import com.ktds.sems.education.vo.EduNoticeSearchVO;
import com.ktds.sems.education.vo.EduNoticeVO;
import com.ktds.sems.education.vo.EduQnaListVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EduReportListVO;
import com.ktds.sems.education.vo.EduReportSearchVO;
import com.ktds.sems.education.vo.EduReportVO;
import com.ktds.sems.education.vo.EducationHistoryListVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationQNAReplyListVO;
import com.ktds.sems.education.vo.EducationQNAReplySearchVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.ReRplyEvalVO;
import com.ktds.sems.education.vo.TeamVO;
import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.file.vo.FileVO;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.web.Paging;

public class EducationServiceImpl implements EducationService {

	private Logger logger = LoggerFactory.getLogger(EducationServiceImpl.class);

	private EducationBiz educationBiz;
	private MemberBiz memberBiz;
	private FileBiz fileBiz;

	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

	public void setEducationBiz(EducationBiz educationBiz) {
		this.educationBiz = educationBiz;
	}

	public void setFileBiz(FileBiz fileBiz) {
		this.fileBiz = fileBiz;
	}

	@Override
	public ModelAndView writeNewEducation(EducationVO educationVO, Errors errors, MultipartHttpServletRequest request) {

		ModelAndView view = new ModelAndView();

		HttpSession session = request.getSession();
		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);

		MultipartFile file = request.getFile("file");

		String fileName = file.getOriginalFilename();
		String salt = SHA256Util.generateSalt();
		String saltFileName = SHA256Util.getEncrypt(fileName, salt) + ".xlsx";
		educationVO.setSalt(salt);

		String filePath = "D:\\" + saltFileName;

		if (memberType.equals("ADM")) {
			if (educationVO.getEducationId() == null) {
				if (errors.hasErrors()) {
					view.setViewName("education/eduregister");
					view.addObject("educationVO", educationVO);
					view.addObject("costList", educationBiz.costCodeList());
					view.addObject("typeList", educationBiz.typeCodeList());
					view.addObject("categoryList", educationBiz.categoryCodeList());
					return view;

				} else {

					boolean result = educationBiz.writeNewEducation(educationVO);

					if (!file.isEmpty() && result) {

						if (fileName.toLowerCase().endsWith(".xlsx")) {

							File files = new File(filePath);

							try {
								file.transferTo(files);

								FileVO fileVO = new FileVO();
								fileVO.setArticleId(educationVO.getEducationId());
								fileVO.setFileName(fileName);
								fileVO.setFileLocation(filePath);
								fileBiz.doWriteFile(fileVO);

							} catch (IllegalStateException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						view.setViewName("redirect:/list");

					} else {
						throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시후 다시 시도해주세요.");
					}
				}
			}
		} else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}

		return view;

	}

	@Override
	public ModelAndView getOneEducationForUpdate(String educationId) {

		EducationVO educationVO = educationBiz.getOneEducation(educationId);

		if (educationVO == null) {
			throw new RuntimeException("해당 강의가 없습니다.");
		}

		ModelAndView view = new ModelAndView();

		view.setViewName("education/update");
		view.addObject("educationVO", educationVO);
		view.addObject("teacherVO", educationBiz.teacherVOList());
		view.addObject("costList", educationBiz.costCodeList());
		view.addObject("typeList", educationBiz.typeCodeList());
		view.addObject("categoryList", educationBiz.categoryCodeList());

		return view;
	}

	@Override
	public ModelAndView modifyNewEducation(EducationVO educationVO, Errors errors,
			MultipartHttpServletRequest request) {
		ModelAndView view = new ModelAndView();

		HttpSession session = request.getSession();
		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);

		MultipartFile file = request.getFile("file");

		String fileName = file.getOriginalFilename();
		String salt = SHA256Util.generateSalt();
		String saltFileName = SHA256Util.getEncrypt(fileName, salt) + ".xlsx";
		educationVO.setSalt(salt);

		String filePath = "D:\\" + saltFileName;

		String educationId = educationVO.getEducationId();

		if (memberType.equals("ADM")) {
			if (errors.hasErrors()) {
				view.setViewName("education/update" + "/" + educationId);
				view.addObject("educationVO", educationVO);
				view.addObject("costList", educationBiz.costCodeList());
				view.addObject("typeList", educationBiz.typeCodeList());
				view.addObject("categoryList", educationBiz.categoryCodeList());
				return view;
			} else {
				boolean result = educationBiz.modifyNewEducation(educationVO);

				if (result) {
					if (!file.isEmpty() && fileName.toLowerCase().endsWith(".xlsx")) {

						File files = new File(filePath);

						try {
							file.transferTo(files);

							FileVO fileVO = new FileVO();
							fileVO.setArticleId(educationVO.getEducationId());
							fileVO.setFileName(fileName);
							fileVO.setFileLocation(filePath);

							fileBiz.updateFile(fileVO);

						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
					view.setViewName("redirect:/detail/" + educationId);
				} else {
					throw new RuntimeException("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
				}
			}
		} else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}

		view.addObject("educationVO", educationVO);
		return view;
	}

	@Override
	public ModelAndView getAllEduCode() {
		ModelAndView view = new ModelAndView();
		view.addObject("teacherVO", educationBiz.teacherVOList());
		view.addObject("costList", educationBiz.costCodeList());
		view.addObject("typeList", educationBiz.typeCodeList());
		view.addObject("categoryList", educationBiz.categoryCodeList());
		view.setViewName("education/eduregister");
		return view;
	}

	@Override
	public ModelAndView getAllEducationHistory(EducationHistorySearchVO eduHistorySearchVO, int pageNo) {

		EducationHistoryListVO eduHistoryListVO = new EducationHistoryListVO();
		Paging paging = new Paging(15, 15);
		eduHistoryListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");

		int eduHistoryCount = educationBiz.getAllEduHistoryCount(eduHistorySearchVO);
		logger.info("eduHistoryCount" + eduHistoryCount);
		if (eduHistoryCount == 0) {
			eduHistoryCount++;
		}
		paging.setTotalArticleCount(eduHistoryCount);
		eduHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
		eduHistorySearchVO.setEndIndex(paging.getEndArticleNumber());

		ModelAndView view = new ModelAndView();
		List<EducationHistoryVO> eduHistoryList = educationBiz.getAllEducationHistory(eduHistorySearchVO);
		eduHistoryListVO.setEducationHistoryList(eduHistoryList);
		view.addObject("eduHistoryListVO", eduHistoryListVO);
		logger.info("eduHistoryListSize" + eduHistoryList.size());
		view.setViewName("education/eduManage");
		return view;
	}

	@Override
	public ModelAndView getJCEduHistory(EducationHistorySearchVO eduHistorySearchVO, int pageNo) {

		logger.info("1" + eduHistorySearchVO.getSearchKeyword());
		logger.info("2" + eduHistorySearchVO.getSearchType());
		logger.info("3" + eduHistorySearchVO.getSearchDate());

		EducationHistoryListVO eduHistoryListVO = new EducationHistoryListVO();
		Paging paging = new Paging(15, 15);
		eduHistoryListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");

		int eduHistoryCount = educationBiz.getJCEduHistoryCount(eduHistorySearchVO);
		logger.info("eduHistoryCount" + eduHistoryCount);
		if (eduHistoryCount == 0) {
			eduHistoryCount++;
		}
		paging.setTotalArticleCount(eduHistoryCount);
		eduHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
		eduHistorySearchVO.setEndIndex(paging.getEndArticleNumber());

		ModelAndView view = new ModelAndView();
		List<EducationHistoryVO> eduHistoryList = educationBiz.getJCEducationHistory(eduHistorySearchVO);
		eduHistoryListVO.setEducationHistoryList(eduHistoryList);

		view.addObject("eduHistoryListVO", eduHistoryListVO);
		logger.info("eduHistoryListSize" + eduHistoryList.size());
		view.setViewName("education/checkApplicant");
		return view;
	}

	@Override
	public ModelAndView applyJoinEducationByMemberId(String educationHistoryId) {

		ModelAndView view = new ModelAndView();

		String state = educationBiz.getStateByEducationHistroyId(educationHistoryId);
		String changeState = "";
		if (state.equals("EDU_JN_A")) {
			changeState = "EDU_JN_C";
		} else if (state.equals("EDU_CL_A")) {
			changeState = "EDU_CL_C";
		} else if (state.equals("EDU_GU_A")) {
			changeState = "EDU_GU_C";
		}
		boolean result = educationBiz.applyJoinEducationByMemberId(educationHistoryId, changeState);

		if (result) {
			view.setViewName("redirect:/educationHistory");
			return view;
		}

		return view;
	}

	@Override
	public ModelAndView cancelJoinEducationByMemberId(String educationHistoryId, String memberId, String description) {

		ModelAndView view = new ModelAndView();

		String state = educationBiz.getStateByEducationHistroyId(educationHistoryId);
		String changeState = "";
		if (state.equals("EDU_JN_A")) {
			changeState = "EDU_JN_R";
		} else if (state.equals("EDU_CL_A")) {
			changeState = "EDU_CL_R";
		} else if (state.equals("EDU_GU_A")) {
			changeState = "EDU_GU_R";
		}

		/**
		 * Email 여기서 보내도록
		 */
		boolean result = educationBiz.cancelJoinEducationByMemberId(educationHistoryId, changeState);

		if (result) {
			view.setViewName("redirect:/educationHistory");
			return view;
		}

		return view;
	}

	/**
	 * Q&A게시판/ 과제게시판/ 강의자료게시판
	 */

	@Override
	public ModelAndView getAllReportArticle(EduReportSearchVO eduReportSearchVO, int pageNo) {
		EduReportListVO eduReportListVO = new EduReportListVO();
		Paging paging = new Paging(20, 20);

		eduReportListVO.setPaging(paging);
		int totalReportCount = educationBiz.getTotalEduReportCount(eduReportSearchVO);

		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalReportCount);

		eduReportSearchVO.setStartIndex(paging.getStartArticleNumber());
		eduReportSearchVO.setEndIndex(paging.getEndArticleNumber());

		List<EduReportVO> eduReport = educationBiz.getAllEduReport(eduReportSearchVO);
		eduReportListVO.setEduReportList(eduReport);

		ModelAndView view = new ModelAndView();
		view.setViewName("education/eduReportPage");
		view.addObject("eduReportListVO", eduReportListVO);
		view.addObject("eduReportSearchVO", eduReportSearchVO);

		return view;
	}

	@Override
	public ModelAndView getAllQnaArticle(EduQnaSearchVO eduQnaSearchVO, int pageNo) {
		EduQnaListVO eduQnaListVO = new EduQnaListVO();
		Paging paging = new Paging(20,20);
		
		eduQnaListVO.setPaging(paging);
		int totalReportCount = educationBiz.getTotalEduQnaCount(eduQnaSearchVO);
		
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalReportCount);
		
		eduQnaSearchVO.setStartIndex(paging.getStartArticleNumber());
		eduQnaSearchVO.setEndIndex(paging.getEndArticleNumber());

		List<EduQnaVO> eduQna = educationBiz.getAllEduQna(eduQnaSearchVO);
		eduQnaListVO.setEduQnaList(eduQna);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("education/eduQnaPage");
		view.addObject("eduQnaListVO", eduQnaListVO);
		view.addObject("eduQnaSearchVO", eduQnaSearchVO);
		view.addObject("searchType", eduQnaSearchVO.getSearchType());
		
		return view;
	}

	@Override
	public ModelAndView getAllEduFileArticle(EduFileSearchVO eduFileSearchVO, int pageNo,
			EduNoticeSearchVO eduNoticeSearchVO) {
		
//		MockHttpSession session = new MockHttpSession();
		EduFileListVO eduFileListVO = new EduFileListVO();
		EduNoticeListVO eduNoticeListVO = new EduNoticeListVO();
		Paging paging = new Paging(20, 20);

		eduFileListVO.setPaging(paging);
		int totalReportCount = educationBiz.getTotalEduFileCount(eduFileSearchVO);
		int totalNoticeCount = educationBiz.getTotalEduFileNoticeCount(eduNoticeSearchVO);
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalReportCount);

		eduFileSearchVO.setStartIndex(paging.getStartArticleNumber());
		eduFileSearchVO.setEndIndex(paging.getEndArticleNumber());

//		if(request.getParameter("searchKeyword") != null){
//			eduFileSearchVO.setPageNo(pageNo);
//			eduFileSearchVO.setSearchKeyword(request.getParameter("searchKeyword"));
//			eduFileSearchVO.setSearchType(request.getParameter("searchType"));
//		}else{
//			eduFileSearchVO =(EduFileSearchVO) session.getAttribute("_SEARCH_");
//			eduFileSearchVO = new EduFileSearchVO();
//			eduFileSearchVO.setStartIndex(paging.getStartArticleNumber());
//			eduFileSearchVO.setEndIndex(paging.getEndArticleNumber());
//			eduFileSearchVO.setPageNo(0);
//			eduFileSearchVO.setSearchKeyword("");
//			eduFileSearchVO.setSearchType("1");
//			
//		}
//		session.setAttribute("_SEARCH_", eduFileSearchVO);
//		
		List<EduFileVO> eduFile = educationBiz.getAllEduFile(eduFileSearchVO);
		eduFileListVO.setEduFileList(eduFile);
		List<EduNoticeVO> eduFileNotice = educationBiz.getAllEduFileNotice(eduNoticeSearchVO);
		eduNoticeListVO.setEduNoticeList(eduFileNotice);

		ModelAndView view = new ModelAndView();
		view.setViewName("education/eduFilePage");
		view.addObject("eduFileListVO", eduFileListVO);
		view.addObject("eduFileSearchVO", eduFileSearchVO);
		view.addObject("eduNoticeListVO", eduNoticeListVO);
		view.addObject("eduNoticeSearchVO", eduNoticeSearchVO);

		return view;
	}

	@Override
	public void rejectionMailAction(String educationHistoryId, String memberId, String description) {

		MemberVO member = memberBiz.getOneMember(memberId);
		String email = member.getEmail();
		String name = member.getName();
		educationBiz.sendEmailRejection(educationHistoryId, memberId, description, email, name);
	}

	@Override
	public int changeEducationApplyState(String educationHistoryId) {
		return educationBiz.changeEducationApplyState(educationHistoryId);
	}

	@Override
	public ModelAndView writeEduFileNoticeAction(EduNoticeVO eduNoticeVO, Errors errors, HttpSession session) {
		ModelAndView view = new ModelAndView();

		MockHttpSession memberSession = new MockHttpSession();
		MemberVO memberVO = (MemberVO) memberSession.getAttribute(Session.MEMBER);

		if (errors.hasErrors()) {
			view.setViewName("education/EduFileNoticeWrtie");
			view.addObject("EduNoticeVO", eduNoticeVO);
			return view;
		} else {
			boolean result = educationBiz.writeEduFileNoticeAction(eduNoticeVO);

			if (result) {
				view.setViewName("redirect:/{educationId}/eduFile");
			} else {
				throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시후 다시 시도해주세요.");
			}
		}

		return view;
	}

	@Override
	public ModelAndView doActionDelete(String educationId, HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);

		if (memberType.equals("ADM")) {
			if (educationId != null) {
				memberVO.setId(memberVO.getId());
				memberVO.setMemberType(memberVO.getMemberType());
				boolean checkPass = educationBiz.doActionDeleteBeforeCheck(memberVO);
				if (checkPass) {
					educationBiz.doActionDelete(educationId);
					educationBiz.emailNoticeForUser(educationId);
					view.setViewName("/");
				} else {
					return new ModelAndView("redirect:/");
				}
			} else {
				throw new RuntimeException("삭제할 교육을 선택해주세요.");
			}
		} else {
			throw new RuntimeException("접근권한이 없습니다.");
		}
		return view;
	}

	@Override
	public ModelAndView viewDetail(String memberId, String eduNoticeId) {

		ModelAndView view = new ModelAndView();

		EduNoticeVO eduNoticeVO = new EduNoticeVO();
		eduNoticeVO = educationBiz.getOneNotice(eduNoticeId);
		educationBiz.addhits(eduNoticeId);

		view.setViewName("education/eduFileNoticeDetail");
		view.addObject("eduNoticeVO", eduNoticeVO);

		return view;
	}

	@Override
	public String doDeleteEduNotice(String educationId, String eduNoticeId) {

		boolean deleteResult = educationBiz.doDeleteEduNotice(eduNoticeId);

		if (deleteResult) {
			return "redirect:/{educationId}/eduFile";
		} else {
			throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시후 다시 시도해주세요.");
		}

	}

	@Override
	public String massiveDeleteNoice(String educationId, String[] deleteNoiceIds) {

		if (deleteNoiceIds != null || deleteNoiceIds.length > 0) {

			for (String deleteNoticeId : deleteNoiceIds) {

				educationBiz.doDeleteEduNotice(deleteNoticeId);
			}
		}
		return "redirect:/{educationId}/eduFile";
	}

	@Override
	public ModelAndView viewModifyNoticePage(String eduNoticeId) {
		
		ModelAndView view = new ModelAndView();
		
		EduNoticeVO eduNotice = educationBiz.getOneNotice(eduNoticeId);
		eduNotice.setContents(eduNotice.getContents().replaceAll("<br/>", "\n"));
		view.setViewName("education/modifyEduFileNotice");
		view.addObject("eduNotice", eduNotice);

		return view;
	}

	@Override
	public ModelAndView doEduFileNoticeModify(String educationId, String eduNoticeId, EduNoticeVO eduNoticeVO,
			Errors errors, HttpSession session) {
	
			ModelAndView view = new ModelAndView();
			
			MockHttpSession memberSession = new MockHttpSession();
			MemberVO memberVO = (MemberVO) memberSession.getAttribute(Session.MEMBER);

			eduNoticeId = eduNoticeVO.getEduNoticeId();
				if (errors.hasErrors()) {
					view.setViewName("education/modifyEduFileNotice"+ "/" + eduNoticeId);
					view.addObject("eduNoticeVO", eduNoticeVO);
					return view;
				} else {
					boolean result = educationBiz.doEduFileNoticeModify(eduNoticeVO);

					if (result) {
						view.setViewName("redirect:/"+educationId+"/eduFileNotice/detail/"+eduNoticeId); 
						
					} else {
						throw new RuntimeException("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
					}
				}

			view.addObject("eduNoticeVO", eduNoticeVO);
			return view;
	
	}

	

	@Override
	public ModelAndView viewReportHistoryPage(EduReportSearchVO reportSearchVO, int pageNo) {
		
		EduReportListVO reportListVO = new EduReportListVO();
		Paging paging = new Paging(10,10);
		reportListVO.setPaging(paging);
		
		int totalReportCount = educationBiz.getTotalEduReportHisotryCount(reportSearchVO);
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalReportCount);
		
		reportSearchVO.setStartIndex(paging.getStartArticleNumber());
		reportSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<EduReportVO> reportList = educationBiz.getAllEduReportHistory(reportSearchVO);
		reportListVO.setEduReportList(reportList);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("education/eduReportHistory");
		view.addObject("reportListVO", reportListVO);
		return view;
	}
	
	/**
	 * @author 206-002 공정민
	 */
	@Override
	public ModelAndView getAllMemberList() {
		List<MemberVO> memberList = educationBiz.getAllMemberList();
		ModelAndView view = new ModelAndView();
		view.setViewName("education/attendanceAllMemberList");
		view.addObject("memberList", memberList);
		return view;
	}
	
	/**
	 * @author 206-002 공정민
	 */
	@Override
	public ModelAndView getOneMemberAttendance(String memberId) {
		List<AttendVO> attendanceList = new ArrayList<AttendVO>();
		List<AttendVO> allAttendanceList = new ArrayList<AttendVO>();
		List<EducationVO> educationInfoList = new ArrayList<EducationVO>();
		
		// 멤버가 수강 중인교육 정보
		educationInfoList = educationBiz.getJoinEducation(memberId);
		
		// 멤버의 출석 이력
		attendanceList = educationBiz.getOneMemberAttendance(memberId);
		
		// 출결 상태 구하기
		allAttendanceList = this.getState(educationInfoList, attendanceList, memberId);
		
		ModelAndView view = new ModelAndView();
		
		if (educationInfoList.size() == 0) {
			view.setViewName("redirect:/attendanceHistory/memberList");
			//view = this.getAllMemberList();
			//view.addObject("errorCode", "NOATD");
		} else {
			view.setViewName("education/attendanceOneMemberList");
			view.addObject("attendanceList", allAttendanceList);
		}
		return view;
	}

	/**
	 * @author 206-002 공정민
	 */
	@Override
	public ModelAndView getAllStartedEducationList() {
		List<EducationVO> educationList = educationBiz.getAllStartedEducationList();
		ModelAndView view = new ModelAndView();
		view.setViewName("education/attendanceAllEducationList");
		view.addObject("educationList", educationList);
		return view;
	}

	/**
	 * @author 206-002 공정민
	 */
	@Override
	public ModelAndView getOneEducationAttendance(String educationId) {
		List<AttendVO> attendanceList = new ArrayList<AttendVO>();
		List<AttendVO> oneMemberAllAttendanceList = new ArrayList<AttendVO>();
		List<EducationVO> educationInfoList = new ArrayList<EducationVO>();
		List<MemberVO> allMemberList = new ArrayList<MemberVO>();
		List<List<AttendVO>> AllMemberAllAttendanceList = new ArrayList<List<AttendVO>>();
		
		// 그 교육을 듣는 학생들
		allMemberList = educationBiz.getAllMemberListByEduId(educationId);
		
		for (MemberVO member : allMemberList) {
			educationInfoList = new ArrayList<EducationVO>();
			
			// 멤버가 수강 중인교육 정보 하나
			educationInfoList.add(educationBiz.getOneEducation(educationId));
			
			// 멤버마다의 출석 이력
			attendanceList = educationBiz.getOneMemberAttendance(member.getId());
						
			// 출결 상태 구하기
			oneMemberAllAttendanceList = this.getState(educationInfoList, attendanceList, member.getId());
			AllMemberAllAttendanceList.add(oneMemberAllAttendanceList);
		}
		
		ModelAndView view = new ModelAndView();
		
		if (educationInfoList.size() == 0) {
			view.setViewName("redirect:/attendanceHistory/educationList");
		} else {
			view.setViewName("education/attendanceOneEduList");
			view.addObject("AllMemberAllAttendanceList", AllMemberAllAttendanceList);
		}
		return view;
	}
	
	// 한 수강생의 출결 이력 구하기
	/**
	 * @author 206-002 공정민
	 */
	public List<AttendVO> getState(List<EducationVO> educationInfoList, List<AttendVO> attendanceList, String memberId) {
		boolean isAttend = false;
		List<AttendVO> allAttendanceList = new ArrayList<AttendVO>();
		AttendVO attendVO = new AttendVO();
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Calendar cal4 = Calendar.getInstance();
		Calendar cal5 = Calendar.getInstance();
		Calendar cal6 = Calendar.getInstance();
		Calendar cal7 = Calendar.getInstance();
		Calendar cal8 = Calendar.getInstance();
		Calendar cal9 = Calendar.getInstance();
		
		String startDate = "";
		String endDate = "";

		Date eduStartDate = new Date();
		Date eduEndDate = new Date();
		Date attendTime = new Date();
		Date leaveTime = new Date();
		Date attendDate = new Date();
		Date eduStartTime = new Date();
		Date eduEndTime = new Date();
		
		long calEduStartDate;
		long calEduEndDate;
		long calEduStartTime;
		long calEduEndTime;
		long calEduBeforeOneHour;
		long calEduHalfTime;
		long calDateVar;
		long calAttendTime;
		long calLeaveTime;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		for (EducationVO educationVO : educationInfoList) {
			startDate = educationVO.getStartDate() + " " + educationVO.getStartTime();
			endDate = educationVO.getEndDate() + " " + educationVO.getEndTime();
			
			try {
				eduStartDate = dateFormat.parse(startDate);
				eduEndDate = dateFormat.parse(endDate);
				eduStartTime = timeFormat.parse(educationVO.getStartTime());
				eduEndTime = timeFormat.parse(educationVO.getEndTime());

				cal.setTime(eduStartDate);
				cal2.setTime(eduEndDate);
				cal4.setTime(eduStartTime);
				cal5.setTime(eduEndTime);
				
				calEduStartDate = cal.getTimeInMillis();
				calEduEndDate = cal2.getTimeInMillis();
				calEduStartTime = cal4.getTimeInMillis();
				calEduEndTime = cal5.getTimeInMillis();

				cal4.add(Calendar.HOUR, -1);
				calEduBeforeOneHour = cal4.getTimeInMillis();
				calEduHalfTime = calEduEndTime - ((calEduEndTime - calEduStartTime) / 2);

				cal6.setTime(eduStartDate);
				calDateVar = calEduStartDate;
				
				while(calDateVar <= calEduEndDate) {
					// 교육 시작일부터 종료일까지 하루하루
					attendVO = new AttendVO();
					
					for (AttendVO attend : attendanceList) {
						// 전체 이력 조회
						attendDate = dateFormat.parse(attend.getAttendTime());
						attendTime = timeFormat.parse(attend.getAttendTime().substring(11));
						leaveTime = timeFormat.parse(attend.getLeaveTime().substring(11));
						
						cal7.setTime(attendTime);
						cal8.setTime(leaveTime);
						cal9.setTime(attendDate);
						
						calAttendTime = cal7.getTimeInMillis();
						calLeaveTime = cal8.getTimeInMillis();
						
						// 해당 날짜가 이력에 있으면
						if (attend.getEducationId().equals(educationVO.getEducationId())
								&& (cal6.get(Calendar.YEAR) == cal9.get(Calendar.YEAR))
								&& (cal6.get(Calendar.MONTH) == cal9.get(Calendar.MONTH))
								&& (cal6.get(Calendar.DATE) == cal9.get(Calendar.DATE))) {
							attendVO = attend;
							isAttend = true;
							
							if ((calEduBeforeOneHour <= calAttendTime)
									&& (calAttendTime <= calEduStartTime)) {
								// 정상출석 : 1시간 전 < 출근시간 < 교육 시작시간 
								attendVO.setState("○");
							}
							else if ((calEduStartTime < calAttendTime) 
									&& (calAttendTime < calEduHalfTime)) {
								// 지각 : 교육 시작시간 < 출근시간 < 절반시간
								attendVO.setState("△");
							}
							else {
								attendVO.setState("X");
							}
							
							if (calLeaveTime < calEduEndTime) {
								// 조퇴 : 퇴근시간 < 종료시간 & 퇴근시간 - 출근시간 >= 4시간
								if ((calLeaveTime - calAttendTime) >= 14400000 ) {
									attendVO.setState("●");
								}
								else {
									// 퇴근시간 - 출근시간 < 4시간이면 결석
									attendVO.setState("X");
								}
							}
						}
					}
					
					if (!isAttend) {
						// 출석 이력이 없다면 결석
						attendVO.setId(0);
						attendVO.setEducationId(educationVO.getEducationId());
						attendVO.setMemberId(memberId);
						attendVO.setAttendTime(String.valueOf(cal6.get(Calendar.YEAR))+"-"
								+String.valueOf(cal6.get(Calendar.MONTH)+1)+"-"
								+String.valueOf(cal6.get(Calendar.DATE))+" (결석)");
						attendVO.setState("X");
						attendVO.setLeaveTime("(결석)");
						attendVO.setEducationTitle(educationVO.getEducationTitle());
					}
					
					allAttendanceList.add(attendVO);
					
					isAttend = false;
					cal6.add(Calendar.DATE, +1);
					calDateVar = cal6.getTimeInMillis();
				}
			} catch (ParseException e) {
				e.getMessage();
				throw new RuntimeException(e);
			}
		}
		return allAttendanceList;
	}

	@Override
	public ModelAndView getAllTeamList() {
		List<TeamVO> teamList = educationBiz.getAllTeamList();
		ModelAndView view = new ModelAndView();
		view.setViewName("education/attendanceAllTeamList");
		view.addObject("teamList", teamList);
		return view;
	}

	@Override
	public ModelAndView getOneTeamAttendance(String educationId, String teamId) {
		List<MemberVO> allMemberList = new ArrayList<MemberVO>();
		List<AttendVO> attendanceList = new ArrayList<AttendVO>();
		List<EducationVO> educationInfoList = new ArrayList<EducationVO>();
		
		List<AttendVO> oneMemberAllAttendanceList = new ArrayList<AttendVO>();
		List<List<AttendVO>> AllMemberAllAttendanceList = new ArrayList<List<AttendVO>>();
		
		// 그 팀에 속한 학생들
		allMemberList = educationBiz.getAllMemberListByTeamId(teamId);
		
		for (MemberVO member : allMemberList) {
			educationInfoList = new ArrayList<EducationVO>();
			
			// 멤버가 수강 중인교육 정보
			educationInfoList = educationBiz.getJoinEducation(member.getId());
			
			// 멤버마다의 출석 이력
			attendanceList = educationBiz.getOneMemberAttendance(member.getId());
						
			// 출결 상태 구하기
			oneMemberAllAttendanceList = this.getState(educationInfoList, attendanceList, member.getId());
			AllMemberAllAttendanceList.add(oneMemberAllAttendanceList);
		}
		
		ModelAndView view = new ModelAndView();
		
		if (educationInfoList.size() == 0) {
			view.setViewName("redirect:/attendanceHistory/teamList");
		} else {
			view.setViewName("education/attendanceOneTeamList");
			view.addObject("AllMemberAllAttendanceList", AllMemberAllAttendanceList);
		}
		return view;
	}

	@Override
	public ModelAndView viewWriteEduQna(String educationId, HttpSession session) {
		ModelAndView view = new ModelAndView();
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberId = memberVO.getId();
		
		boolean confirmMemberOfEdu = educationBiz.confirmMemberOfEdu(educationId, memberId);
		if ( confirmMemberOfEdu || memberVO.getMemberType().equals("ADM") || memberVO.getMemberType().equals("TR") ) {
			view.addObject("educationId", educationId);
			view.setViewName("education/eduQnaWrite");
		}else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		
		return view;
	}

	@Override
	public ModelAndView doWriteEduQnaAction(EduQnaVO eduQnaVO, HttpSession session) {
		ModelAndView view = new ModelAndView();
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberId = memberVO.getId();
		
		String nowDate = educationBiz.getNowDate();
		int nextEqbSeq = educationBiz.getNextEqbSeq();
		String eduQnaId = "EQ-" + nowDate + "-" + lpad(nextEqbSeq + "", 6, "0");
		
		eduQnaVO.setMemberId(memberId);
		eduQnaVO.setEduQnaId(eduQnaId);
		
		boolean confirmMemberOfEdu = educationBiz.confirmMemberOfEdu(eduQnaVO.getEducationId(), memberId);
		
		if ( confirmMemberOfEdu || memberVO.getMemberType().equals("ADM") || memberVO.getMemberType().equals("TR") ) {
			boolean result = educationBiz.insertEduQna(eduQnaVO);
			
			if( result ) {
				view.setViewName("redirect:/"+eduQnaVO.getEducationId()+"/eduQna");
			}else {
				throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
		}else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		
		return view;
	}

	@Override
	public ModelAndView detailOfEduQna(String eduQnaId, String educationId, HttpSession session, int pageNo) {
		ModelAndView view = new ModelAndView();
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberId = memberVO.getId();
		String memberType = memberVO.getMemberType();
		
		boolean confirmMemberOfEdu = educationBiz.confirmMemberOfEdu(educationId, memberId);
		if ( confirmMemberOfEdu || memberVO.getMemberType().equals("ADM") || memberVO.getMemberType().equals("TR") ) {

			EduQnaVO eduQnaVO = educationBiz.detailOfEduQna(eduQnaId);
			EducationQNAReplyListVO qnaReplyList = new EducationQNAReplyListVO();
			
			Paging paging = new Paging(5,10);
			qnaReplyList.setPaging(paging);
			paging.setPageNumber(pageNo + "");
			
			int totalQNAReplyCount = educationBiz.getTotalQnaEduReplyCount(eduQnaId);
			paging.setTotalArticleCount(totalQNAReplyCount);
			
			EducationQNAReplySearchVO searchVO = new EducationQNAReplySearchVO();
			searchVO.setStartIndex(paging.getStartArticleNumber());
			searchVO.setEndIndex(paging.getEndArticleNumber());
			searchVO.setAtcId(eduQnaId);
			
			List<EducationQNAReplyVO> qnaReplyListByAtcId = educationBiz.getAllQNAReplyListByAtcId(searchVO);
			
			qnaReplyList.setQnaReplyList(qnaReplyListByAtcId);

			view.addObject("qnaReplyList", qnaReplyList);

			if( eduQnaVO != null ) {
				boolean result = educationBiz.addHitsToEduQna(eduQnaId);
				if( result ) {
					view.addObject("eduQnaVO", eduQnaVO);
					view.addObject("memberType", memberType);
					view.setViewName("education/eduQnaDetail");
				}				
			}else {
				throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
		}else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		
		return view;
	}

	@Override
	public ModelAndView doEduQnaReplyAction(EducationQNAReplyVO eduBBSReplyVO, Errors errors, HttpSession session,
			String educationId) {
		ModelAndView view = new ModelAndView();
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		String atcId = eduBBSReplyVO.getAtcId();
		
		String nowDate = educationBiz.getNowDate();
		int nextSeq = educationBiz.getNextReplySeq();
		String realReplyId = "ER-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		
		eduBBSReplyVO.setMbrId(sessionMember.getId());
		eduBBSReplyVO.setAtcId(atcId);
		eduBBSReplyVO.setReplyId(realReplyId);
		
		if ( !errors.hasErrors() ) {
			educationBiz.addQNAReply(eduBBSReplyVO);
			view.setViewName("redirect:/detailOfEduQna/"+atcId+"/"+educationId);
			
			EduQnaVO eduQnaVO = educationBiz.detailOfEduQna(atcId);
			
			String toEmail = educationBiz.getEmail(eduQnaVO.getMemberId());//질문자 mail주소
			String fromEmail = educationBiz.getEmail(sessionMember.getId());//답변자 아이디
			
			//mail 전송 메소드
			//educationBiz.sendEmailInEduQna(toEmail, fromEmail, eduQnaVO, eduBBSReplyVO);
			
			
		}else {
			throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시 후 다시 시도해주세요.");
		}
		
		return view;
	}

	@Override
	public String addQnaEduReplyLike(String replyId, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		ReRplyEvalVO reRplyEvalVO = new ReRplyEvalVO();
		
		String nowDate = educationBiz.getNowDate();
		int nextSeq = educationBiz.getNextReReplyEval();
		String replyEvalId = "RE-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		
		//댓글ID
		reRplyEvalVO.setReplyId(replyId);
		
		// 싫어요 누른 아이디
		reRplyEvalVO.setMbrId(memberVO.getId());
		
		// REPLY_EVAL_ID (pk)
		reRplyEvalVO.setReplyEvalId(replyEvalId);
		
		if (!educationBiz.checkReReplyEval(reRplyEvalVO)){
			boolean result = educationBiz.insertReReplyEvalByDislike(reRplyEvalVO);
			
			if(!result){
				return "FAIL";
			}
			else{
				if(educationBiz.addQnaEduReplyLike(replyId)){
					return "OK";
				}
				else {
					return "FAIL";
				}
			} 
		}
		else {
			return "FAIL";
		}
	}

	@Override
	public String addQnaEduReplyDisLike(String replyId, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		ReRplyEvalVO reRplyEvalVO = new ReRplyEvalVO();
		
		String nowDate = educationBiz.getNowDate();
		int nextSeq = educationBiz.getNextReReplyEval();
		String replyEvalId = "RE-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		
		//댓글ID
		reRplyEvalVO.setReplyId(replyId);
		
		// 싫어요 누른 아이디
		reRplyEvalVO.setMbrId(memberVO.getId());
		
		// REPLY_EVAL_ID (pk)
		reRplyEvalVO.setReplyEvalId(replyEvalId);
		
		if (!educationBiz.checkReReplyEval(reRplyEvalVO)){
			boolean result = educationBiz.insertReReplyEvalByDislike(reRplyEvalVO);
			
			if(!result){
				return "FAIL";
			}
			else{
				if(educationBiz.addQnaEduReplyDisLike(replyId)){
					return "OK";
				}
				else {
					return "FAIL";
				}
			} 
		}
		else {
			return "FAIL";
		}
	}
	private String lpad(String source, int length, String defValue) {
		int sourceLength = source.length();
		int needLength = length - sourceLength;
		
		for (int i = 0; i < needLength; i++) {
			source = defValue + source;
		}
		return source;
		
	}

}