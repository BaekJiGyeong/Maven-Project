package kr.co.hucloud.security.code.example.board.service.impl;

import kr.co.hucloud.security.code.example.board.dao.BoardDAO;
import kr.co.hucloud.security.code.example.board.service.BoardService;
import kr.co.hucloud.security.code.example.board.vo.ArticleSearchVO;
import kr.co.hucloud.security.code.example.board.vo.BoardListVO;
import kr.co.hucloud.security.code.example.board.vo.BoardVO;
import kr.co.hucloud.security.code.example.common.util.Paging;

public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDao;

	public void setBoardDao(BoardDAO boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public BoardListVO getBoardById(String id) {
		return boardDao.getBoardById(id);
	}
	
	@Override
	public void insertArticleInBoard(BoardVO board) {
		boardDao.insertArticleInBoard(board);
	}
	
	public void updateHit(String id) {
		boardDao.updateHit(id);
	}
	
	public void updateRecommend(String id) {
		boardDao.updateRecommend(id);
	}
	
	@Override
	public BoardListVO getArticleInBoardByCondition(ArticleSearchVO articleSearch) {
		
		int totalCount = boardDao.getArticleCountInBoardByCondition(articleSearch);
		if(totalCount == 0) {
			return null;
		}
		
		Paging paging = new Paging();
		paging.setTotalArticleCount(totalCount);
		paging.setPageNumber(articleSearch.getPageNO());
		
		articleSearch.setStartNumber(paging.getStartArticleNumber());
		articleSearch.setEndNumber(paging.getEndArticleNumber());
		
		BoardListVO result = boardDao.getArticleInBoardByCondition(articleSearch);
		result.setPaging(paging);
		
		return result;
	}
	
	@Override
	public void deleteArticle(String id) {
		boardDao.deleteArticle(id);
	}
	
	@Override
	public void modifyArticleInBoard(BoardVO board) {
		boardDao.modifyArticleInBoard(board);
	}
	
}
