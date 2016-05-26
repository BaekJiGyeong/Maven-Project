package com.ktds.sems.team.dao;

import java.util.List;

import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;

public interface TeamDAO {

	public int getTotalteamCount();

	public List<TeamVO> getAllTeamList(TeamSearchVO searchVO);
	
	public int addNewTeamBBSArticle(TeamBBSVO teamBBS);

	public int getNextTeamBBSSeq();

	public String getSysDate();

	public List<TeamBBSVO> getTeamBBSList(TeamSearchVO searchVO);

	public int getSearchedBBSCount();

	public List<TeamBBSVO> doSearchList(TeamBBSVO teamBBSVO, TeamSearchVO searchVO);

	public String getStartYear();

	public String getEndYear();

	public List<TeamsListVO> getOneTeamDetail(String teamId);
	
	public TeamBBSVO getTeamBBS(String teamBBSId);

	public int addHitsRecord(TeamBBSVO bbs);

	public int isAlreadyCheckBBS(TeamBBSVO bbs);

	public int getNextBBSHistorySeq();

	public int checkDislikeByTeamBBSVO(TeamBBSVO bbs);

	public int addLikeRecord(TeamBBSVO bbs);

	public int checkLikeByTeamBBSVO(TeamBBSVO bbs);

	public int addDislikeRecord(TeamBBSVO bbs);

	public List<String> getFileInfo(String teamBBSId);
	
	public String getLikeState(TeamBBSVO bbs);

	public String getDislikeState(TeamBBSVO bbs);

	public int addLikeCount(TeamBBSVO bbs);

	public int addDislikeCount(TeamBBSVO bbs);

	public int writeBBSReply(TeamBBSReplyVO replyVO);

	public List<TeamBBSReplyVO> getTeamBBSReplies(String teamBBSId);

	public int getNextTeamBBSReplySeq();

	public int writeBBSReReply(TeamBBSReplyVO replyVO);

	public int getNextOrderNoByParentId(String parentReplyId);

	public int insertNewMinutes(MinutesVO minutesVO);

	public List<MinutesVO> getAllMinutesList(MinutesSearchVO minutesSearchVO);

	public int doModifyAction(TeamBBSVO teamBBS);

	public String getSaltById(String sessionId);

	public String getPasswordById(String sessionId);

	public int doDeleteBBS(String teamBBSId);

	public List<String> isReplyByTeamBBSId(String teamBBSId);

	public int getTotalMinutesCount(MinutesSearchVO minutesSearchVO);

	public int nextMinutesSeq();

	public List<MinutesVO> getAllMinutes(MinutesSearchVO minutesSearchVO);
	
	public List<MemberVO> getAllEduMember(String educationId);

	public boolean bulidTeam(String educationId, String teamName);

	public boolean insertMember(String memberId);

	public int getTotalMinutesCountForAdmin(MinutesSearchVO minutesSearchVO);

}
