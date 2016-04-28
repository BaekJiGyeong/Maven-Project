package kr.co.hucloud.security.code.example.board.vo;

import java.util.ArrayList;
import java.util.List;

import kr.co.hucloud.security.code.example.common.util.Paging;

public class BoardListVO {

	private List<BoardVO> boardList;
	private Paging paging;
	
	public BoardListVO() {
		this.boardList = new ArrayList<BoardVO>();
	}
	
	public BoardListVO(List<BoardVO> boardList) {
		this();
		this.boardList.addAll(boardList);
	}
	
	public BoardVO boardAt(int i) {
		return this.boardList.get(i);
	}
	
	public void removeAt(int i) {
		this.boardList.remove(i);
	}
	
	public void add(BoardVO e) {
		this.boardList.add(e);
	}
	
	public int size() {
		return this.boardList.size();
	}
	
	public List<BoardVO> getList() {
		List<BoardVO> result = new ArrayList<BoardVO>();
		result.addAll(this.boardList);
		
		return result;
	}

	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
}
