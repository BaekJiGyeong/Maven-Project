package kr.co.hucloud.security.code.example.board.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.hucloud.security.code.example.board.service.BoardService;
import kr.co.hucloud.security.code.example.board.vo.ArticleSearchVO;
import kr.co.hucloud.security.code.example.board.vo.BoardListVO;
import kr.co.hucloud.security.code.example.board.vo.BoardVO;
import kr.co.hucloud.security.code.example.common.Session;
import kr.co.hucloud.security.code.example.common.web.DownloadUtil;
import kr.co.hucloud.security.code.example.member.vo.MemberVO;
import kr.co.hucloud.security.code.example.reply.service.ReplyService;
import kr.co.hucloud.security.code.example.reply.vo.ReplyVO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.lucy.security.xss.XssFilter;

@Controller
public class BoardController {
	
	private static final String FILE_PATH = "D:\\";
	
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
	public ModelAndView writeArticle(HttpServletRequest request, BoardVO board, HttpSession session) {
		
		String storedCsrfToken = (String)session.getAttribute(Session.CSRF_TOKEN);
		String requestedCsrfToken = request.getParameter("csrfToken");
		
		if(storedCsrfToken == null || storedCsrfToken.equals(requestedCsrfToken)) {
			return new ModelAndView("redirect:/board/write");
		}
		
		String userId = ((MemberVO) session.getAttribute(Session.MEMBER)).getId();
		board.setUserId(userId);
		
		MultipartFile uploadedFile = board.getFile();
		
		if( !uploadedFile.isEmpty() ) {
			
			String fileName = uploadedFile.getOriginalFilename();
			
			if ( fileName.toLowerCase().endsWith(".jpg") 
					|| fileName.toLowerCase().endsWith(".jpeg") 
					|| fileName.toLowerCase().endsWith(".png")
					|| fileName.toLowerCase().endsWith(".gif")
					|| fileName.toLowerCase().endsWith(".bmp")) {
				
				// 외부 URL 접근이 가능함.
				File file = new File(FILE_PATH, uploadedFile.getOriginalFilename());
				try {
					uploadedFile.transferTo(file);
					board.setFileName(uploadedFile.getOriginalFilename());
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		boardService.insertArticleInBoard(board);
		
		ModelAndView view = new ModelAndView("redirect:/board/list");
		return view;
	}
	
	@RequestMapping("/board/download/{articleId}")
	public void download(@PathVariable int articleId, HttpServletRequest request, HttpServletResponse response) {
		
		BoardVO board = boardService.getBoardById(articleId + "").getList().get(0);
		DownloadUtil download = DownloadUtil.getInstance(FILE_PATH);
		
		try {
			download.download(request, response, board.getFileName(), board.getFileName());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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
			// XSS 게시판의 제목과 내용에 대해 XSS HTML 인코딩
			String content = boardList.getList().get(0).getContent();
			String subject = boardList.getList().get(0).getContent();
			
			XssFilter xssFilter = XssFilter.getInstance("/lucy-xss-superset.xml");
			content = xssFilter.doFilter(content);
			content = xssFilter.doFilter(subject);
			
			boardList.getList().get(0).setContent(content);
			boardList.getList().get(0).setSubject(subject);
			
			view.addObject("article", (BoardVO)boardList.getList().get(0));
			
			// XSS 게시판 댓글에 대해 XSS HTML 인코딩
			List<ReplyVO> replyList = replyService.getAllReplyByBoardId(id);
			for (ReplyVO reply : replyList) {
				content = reply.getContent();
				content = xssFilter.doFilter(content);
				reply.setContent(content);
			}
			
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
