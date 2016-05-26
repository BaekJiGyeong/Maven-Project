package com.ktds.sems.education.service;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EduFileSearchVO;
import com.ktds.sems.education.vo.EduNoticeSearchVO;
import com.ktds.sems.education.vo.EduNoticeVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EduReportSearchVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.education.vo.EducationVO;

public interface EducationService {
	
	public ModelAndView writeNewEducation(EducationVO educationVO, Errors errors , MultipartHttpServletRequest request);

	public ModelAndView getOneEducationForUpdate(String educationId);

	public ModelAndView modifyNewEducation(EducationVO educationVO, Errors errors, MultipartHttpServletRequest request);

	public ModelAndView getAllEduCode();

	public ModelAndView getAllEducationHistory(EducationHistorySearchVO eduHistorySearchVO, int pageNo);

	public ModelAndView getJCEduHistory(EducationHistorySearchVO eduHistorySearchVO, int pageNo);

	public ModelAndView applyJoinEducationByMemberId(String educationHistoryId);

	public void rejectionMailAction(String educationHistoryId, String memberId, String description);

	public ModelAndView cancelJoinEducationByMemberId(String educationHistoryId, String memberId, String description);

	public ModelAndView getAllReportArticle(EduReportSearchVO eduReportSearchVO, int pageNo);

	public ModelAndView getAllQnaArticle(EduQnaSearchVO qnaArticleSearchVO, int pageNo);

	public ModelAndView getAllEduFileArticle(EduFileSearchVO eduFileSearchVO, int pageNo
			, EduNoticeSearchVO eduNoticeSearchVO);
	
	public int changeEducationApplyState(String educationHistoryId);

	public ModelAndView writeEduFileNoticeAction(EduNoticeVO eduNoticeVO, Errors errors,  HttpSession session);

	public ModelAndView doActionDelete(String educationId, HttpSession session);

	public ModelAndView viewReportHistoryPage(EduReportSearchVO reportSearchVO, int pageNo);
	
	public ModelAndView viewDetail(String memberId, String eduNoticeId);

	public String doDeleteEduNotice(String educationId, String eduNoticeId);

	public String massiveDeleteNoice(String educationId, String[] deleteNoiceIds);

	public ModelAndView viewModifyNoticePage(String eduNoticeId);

	public ModelAndView doEduFileNoticeModify(String educationId, String eduNoticeId, EduNoticeVO eduNoticeVO,
			Errors errors, HttpSession session);

	public ModelAndView getAllMemberList();

	public ModelAndView getOneMemberAttendance(String memberId);

	public ModelAndView getAllStartedEducationList();

	public ModelAndView getOneEducationAttendance(String educationId);

	public ModelAndView getAllTeamList();

	public ModelAndView getOneTeamAttendance(String educationId, String teamId);

	public ModelAndView viewWriteEduQna(String educationId, HttpSession session);

	public ModelAndView doWriteEduQnaAction(EduQnaVO eduQnaVO, HttpSession session);

	public ModelAndView detailOfEduQna(String eduQnaId, String educationId, HttpSession session, int pageNo);

	public ModelAndView doEduQnaReplyAction(EducationQNAReplyVO eduBBSReplyVO, Errors errors, HttpSession session,
			String educationId);

	public String addQnaEduReplyLike(String replyId, HttpSession session);

	public String addQnaEduReplyDisLike(String replyId, HttpSession session);
}
