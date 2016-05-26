package com.ktds.sems.team.biz;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;

public interface TeamBiz {

	public int getTotalTeamCount();
	
	public List<TeamVO> getAllTeamList(TeamSearchVO searchVO);
	
	public boolean addNewTeamBBSArticle(TeamBBSVO teamBBS, MultipartHttpServletRequest request);

	public List<TeamBBSVO> getTeamBBSList(TeamSearchVO searchVO);

	public int getSearchedBBSCount();

	public List<TeamBBSVO> doSearchList(TeamBBSVO teamBBSVO, TeamSearchVO searchVO);

	public String getStartYear();

	public String getEndYear();

	public List<TeamsListVO> getOneTeamDetail(String teamId);

	public TeamBBSVO getTeamBBS(String teamBBSId);

	public boolean addHitsRecord(TeamBBSVO bbs);
	
	public boolean checkDislikeByTeamBBSVO(TeamBBSVO bbs);

	public boolean addLikeRecord(TeamBBSVO bbs);

	public boolean checkLikeByTeamBBSVO(TeamBBSVO bbs);

	public boolean addDislikeRecord(TeamBBSVO bbs);

	public List<String> getFileInfo(String teamBBSId);

	public String getLikeState(TeamBBSVO bbs);

	public String getDislikeState(TeamBBSVO bbs);

	public boolean addLikeCount(TeamBBSVO bbs);

	public boolean addDislikeCount(TeamBBSVO bbs);

	public boolean writeBBSReply(TeamBBSReplyVO replyVO);

	public List<TeamBBSReplyVO> getTeamBBSReplies(String teamBBSId);

	public boolean writeBBSReReply(TeamBBSReplyVO replyVO);

	public boolean writeNewMinutes(MinutesVO minutesVO);

	public List<MinutesVO> getAllMinutesList(MinutesSearchVO minutesSearchVO);

	public List<MemberVO> getAllEduMember(String educationId);

	public boolean bulidTeam(String educationId, String teamName);

	public boolean insertMember(String memberId);

	public int getTotalMinutesCount(MinutesSearchVO minutesSearchVO);

	public List<MinutesVO> getAllMinutes(MinutesSearchVO minutesSearchVO);

	int getTotalMinutesCountForAdmin(MinutesSearchVO minutesSearchVO);

	public boolean doModifyAction(TeamBBSVO teamBBS);

	public String getSaltById(String sessionId);

	public String getPasswordById(String sessionId);

	public boolean doDeleteBBS(String teamBBSId);

	public boolean isReplyByTeamBBSId(String teamBBSId);

}
