package kr.co.hucloud.security.code.example.reply.service.impl;

import java.util.List;

import kr.co.hucloud.security.code.example.reply.dao.ReplyDAO;
import kr.co.hucloud.security.code.example.reply.service.ReplyService;
import kr.co.hucloud.security.code.example.reply.vo.ReplyVO;

public class ReplyServiceImpl implements ReplyService {

	private ReplyDAO replyDao;

	public void setReplyDao(ReplyDAO replyDao) {
		this.replyDao = replyDao;
	}
	
	@Override
	public void insertReply(ReplyVO reply) {
		replyDao.insertReply(reply);
	}
	
	@Override
	public List<ReplyVO> getAllReplyByBoardId(String id) {
		return replyDao.getAllReplyByBoardId(id);
	}
	
	@Override
	public void deleteReply(String id) {
		replyDao.deleteReply(id);
	}
	
	@Override
	public void updateRecommend(String boardId, String id) {
		replyDao.updateRecommend(boardId, id);
	}
}
