package kr.co.hucloud.security.code.example.reply.service;

import java.util.List;

import kr.co.hucloud.security.code.example.reply.vo.ReplyVO;

public interface ReplyService {

	public void insertReply(ReplyVO reply);

	public List<ReplyVO> getAllReplyByBoardId(String id);

	public void deleteReply(String id);

	public void updateRecommend(String boardId, String id);

}
