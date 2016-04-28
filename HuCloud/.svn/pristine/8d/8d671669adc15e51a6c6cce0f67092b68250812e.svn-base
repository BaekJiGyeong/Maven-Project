package kr.co.hucloud.security.code.example.board.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.hucloud.security.code.example.board.service.BoardService;
import kr.co.hucloud.security.code.example.board.vo.ArticleSearchVO;
import kr.co.hucloud.security.code.example.board.vo.BoardListVO;
import kr.co.hucloud.security.code.example.board.vo.BoardVO;
import kr.co.hucloud.security.code.example.common.Session;
import kr.co.hucloud.security.code.example.member.vo.MemberVO;
import kr.co.hucloud.security.code.example.reply.service.ReplyService;
import kr.co.hucloud.security.code.example.reply.vo.ReplyVO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	
	private static final String FILE_PATH = "C:/Users/mcjan/Documents/workspace-sts-3.7.2.RELEASE/HuCloud/src/main/webapp/WEB-INF/resources/uploadFiles";
	
	private BoardService boardService;
	private ReplyService replyService;
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	@RequestMapping("/board/write")
	public String viewWritePage() {
		return "board/write";
	}
	
	@RequestMapping("/board/writeArticle")
	public ModelAndView writeArticle(BoardVO board, HttpSession session) {
		
		String userId = ((MemberVO) session.getAttribute(Session.MEMBER)).getId();
		board.setUserId(userId);
		
		MultipartFile uploadedFile = board.getFile();
		
		if( !uploadedFile.isEmpty() ) {
			//FIXME 외부 URL 접근이 가능함.
			File file = new File(FILE_PATH, uploadedFile.getOriginalFilename());
			try {
				uploadedFile.transferTo(file);
				board.setFileName(uploadedFile.getOriginalFilename());
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		boardService.insertArticleInBoard(board);
		
		ModelAndView view = new ModelAndView("redirect:/board/list");
		return view;
	}
	
	@RequestMapping("/board/list")
	public ModelAndView viewList(
				@RequestParam(defaultValue="") String pageNo,
				@RequestParam(defaultValue="") String searchType,
				@RequestParam(defaultValue="") String searchKeyword,
				HttpSession session
			) {
		
		ArticleSearchVO articleSearch = null;
		if(pageNo.equals("")) {
			articleSearch = (ArticleSearchVO) session.getAttribute(Session.SEARCH_LIST);
		}
		if(articleSearch == null) {
			articleSearch = new ArticleSearchVO(pageNo, searchType, searchKeyword);
		}
		
		session.setAttribute(Session.SEARCH_LIST, articleSearch);
		
		BoardListVO boardList = boardService.getArticleInBoardByCondition(articleSearch);
		
		ModelAndView view = new ModelAndView("board/list");
		
		if(boardList != null) {
			//FIXME XSS 게시판의 제목과 내용에 대해 XSS HTML 인코딩
			view.addObject("list", boardList.getList());
			view.addObject("paging", boardList.getPaging().getPagingList("pageNo", "[@]", "[이전]", "[다음]", ""));
		}
		
		return view;
	}
	
	
	@RequestMapping("/board/read/{id}")
	public ModelAndView read(@PathVariable String id) {
		boardService.updateHit(id);
		return new ModelAndView("redirect:/board/article/" + id);
	}
	
	@RequestMapping("/board/article/{id}")
	public ModelAndView detail(@PathVariable String id) {
		
		BoardListVO boardList = boardService.getBoardById(id);
		
		ModelAndView view = new ModelAndView("board/detail");
		
		if(boardList != null) {
			//FIXME XSS 게시판의 제목과 내용에 대해 XSS HTML 인코딩
			view.addObject("article", (BoardVO)boardList.getList().get(0));
			
			//FIXME XSS 게시판 댓글에 대해 XSS HTML 인코딩
			List<ReplyVO> replyList = replyService.getAllReplyByBoardId(id);
			view.addObject("replyList", replyList);
		}
		
		return view;
	}
	
	@RequestMapping("/board/recommend/{id}")
	public ModelAndView recommend(@PathVariable String id) {
		boardService.updateRecommend(id);
		
		return new ModelAndView("redirect:/board/article/" + id);
	}
	
	@RequestMapping("/board/delete/{id}")
	public ModelAndView delete(@PathVariable String id) {
		//FIXME 잘못된 접근 제어 --> 요청자의 ID와 글 작성자의 ID를 비교해야 함
		replyService.deleteReply(id);
		boardService.deleteArticle(id);
		return new ModelAndView("redirect:/board/list");
	}
	
	@RequestMapping("/board/modify/{id}")
	public ModelAndView modify(@PathVariable String id) {
		//FIXME 잘못된 접근 제어 --> 요청자의 ID와 글 작성자의 ID를 비교해야 함
		BoardListVO boardList = boardService.getBoardById(id);
		ModelAndView view = new ModelAndView("board/modify");
		
		if(boardList != null) {
			view.addObject("article", (BoardVO)boardList.getList().get(0));
		}
		
		return view;
	}
	
	@RequestMapping("/board/modifyArticle")
	public ModelAndView modifyArticle(BoardVO board, HttpSession session) {
		
		String userId = ((MemberVO) session.getAttribute(Session.MEMBER)).getId();
		board.setUserId(userId);
		
		MultipartFile uploadedFile = board.getFile();
		
		if(board.isDelete()) {
			File file = new File(FILE_PATH, board.getFileName());
			file.delete();
			board.setFileName("");
		}
		
		if( !uploadedFile.isEmpty() ) {
			//FIXME 외부 URL 접근이 가능함.
			File file = new File(FILE_PATH, uploadedFile.getOriginalFilename());
			try {
				uploadedFile.transferTo(file);
				board.setFileName(uploadedFile.getOriginalFilename());
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		boardService.modifyArticleInBoard(board);
		
		ModelAndView view = new ModelAndView("redirect:/board/article/" + board.getId());
		return view;
	}
	
}
