package com.ktds.sems.education.service;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;

public interface EducationService {

	public ModelAndView getOneEducationDetail(String educationId, HttpSession session);
	
	public ModelAndView getAllEducationList(int pageNo);

	public String doApplyEducation(String educationId, String educationType, HttpSession session);
	
	public ModelAndView writeNewComment(HttpSession session, QNAVO qnaVO, Errors errors, String educationId);

	public ModelAndView doSearchList(EducationVO educationVO, int pageNo);


	public String doCancelEducation(String educationId, HttpSession session);
	
	
}
