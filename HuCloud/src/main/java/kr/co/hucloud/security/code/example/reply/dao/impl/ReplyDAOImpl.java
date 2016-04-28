package kr.co.hucloud.security.code.example.reply.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.hucloud.security.code.example.reply.dao.ReplyDAO;
import kr.co.hucloud.security.code.example.reply.vo.ReplyVO;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class ReplyDAOImpl extends SqlSessionDaoSupport implements ReplyDAO {

	@Override
	public void insertReply(ReplyVO reply) {
		getSqlSession().insert("ReplyDAO.insertReply", reply);
	}
	
	@Override
	public List<ReplyVO> getAllReplyByBoardId(String id) {
		List<ReplyVO> result = getSqlSession().selectList("ReplyDAO.getReplyByBoardIdMap", Integer.parseInt(id));
		return result;
	}
	
	@Override
	public void deleteReply(String id) {
		getSqlSession().delete("ReplyDAO.deleteReply", id);
	}
	
	@Override
	public void updateRecommend(String boardId, String id) {
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("boardId", boardId);
		paramMap.put("id", id);
		
		getSqlSession().update("ReplyDAO.updateRecommend", paramMap);
	}
	
}
