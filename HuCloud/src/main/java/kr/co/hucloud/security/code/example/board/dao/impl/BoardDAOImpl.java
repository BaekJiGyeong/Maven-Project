package kr.co.hucloud.security.code.example.board.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.hucloud.security.code.example.board.dao.BoardDAO;
import kr.co.hucloud.security.code.example.board.vo.ArticleSearchVO;
import kr.co.hucloud.security.code.example.board.vo.BoardListVO;
import kr.co.hucloud.security.code.example.board.vo.BoardVO;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class BoardDAOImpl extends SqlSessionDaoSupport implements BoardDAO {

	public void updateHit(String id) {
		getSqlSession().update("BoardDAO.updateHit", id);
	}
	
	@Override
	public BoardListVO getBoardById(String id) {
		List<BoardVO> list = getSqlSession().selectList("BoardDAO.getBoardById", id);
		BoardListVO result = new BoardListVO(list);
		return result;
	}
	
	public void updateRecommend(String id) {
		getSqlSession().update("BoardDAO.updateRecommend", id);
	}
	
	@Override
	public void insertArticleInBoard(BoardVO board) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("subject", board.getSubject());
		paramMap.put("content", board.getContent());
		paramMap.put("userId", board.getUserId());
		paramMap.put("fileName", board.getFileName());
		getSqlSession().insert("BoardDAO.insertArticleInBoard", paramMap);
	}
	
	@Override
	public BoardListVO getArticleInBoardByCondition(ArticleSearchVO articleSearch) {
		List<BoardVO> list = getSqlSession().selectList("BoardDAO.getArticleInBoardByCondition", articleSearch);
		BoardListVO result = new BoardListVO(list);
		return result;
	}
	
	@Override
	public int getArticleCountInBoardByCondition(ArticleSearchVO articleSearch) {
		int count = getSqlSession().selectOne("BoardDAO.getArticleCountInBoardByCondition", articleSearch);
		return count;
	}
	
	@Override
	public void deleteArticle(String id) {
		getSqlSession().delete("BoardDAO.deleteArticle", id);
	}
	
	@Override
	public void modifyArticleInBoard(BoardVO board) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("subject", board.getSubject());
		paramMap.put("content", board.getContent());
		paramMap.put("userId", board.getUserId());
		paramMap.put("fileName", board.getFileName());
		paramMap.put("id", board.getId() + "");
		getSqlSession().update("BoardDAO.modifyArticleInBoard", paramMap);
	}
}
