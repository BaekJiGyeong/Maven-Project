package kr.co.hucloud.security.code.example.board.dao;

import kr.co.hucloud.security.code.example.board.vo.ArticleSearchVO;
import kr.co.hucloud.security.code.example.board.vo.BoardListVO;
import kr.co.hucloud.security.code.example.board.vo.BoardVO;

public interface BoardDAO {

	public BoardListVO getBoardById(String id);

	public void insertArticleInBoard(BoardVO board);

	public void updateHit(String id);
	
	public void updateRecommend(String id);
	
	public BoardListVO getArticleInBoardByCondition(ArticleSearchVO articleSearch);

	public int getArticleCountInBoardByCondition(ArticleSearchVO articleSearch);

	public void deleteArticle(String id);

	public void modifyArticleInBoard(BoardVO board);
	
}
