package kr.co.hucloud.security.code.example.attack.xss.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.hucloud.security.code.example.board.service.BoardService;
import kr.co.hucloud.security.code.example.board.vo.BoardListVO;
import kr.co.hucloud.security.code.example.common.util.SendMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.lucy.security.xss.XssFilter;

@Controller
public class XSSController {

	private BoardService boardService;

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	@RequestMapping("/attack/xss")
	public String xss() {
		return "attack/xss/xss";
	}
	
	@RequestMapping("/attack/xss/attack1")
	public ModelAndView attack1(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("attack/xss/xss");
		
		String requestedString = request.getParameter("script");
		
		XssFilter xssFilter = XssFilter.getInstance("/lucy-xss-superset.xml");
		requestedString = xssFilter.doFilter(requestedString);
		
		view.addObject("result", requestedString);
		// XSS Case 1. 결과에 대해 HTML Encoding 처리하기
		view.addObject("requestedString1", requestedString);
		
		return view;
	}
	
	@RequestMapping("/attack/xss/attack2")
	public ModelAndView attack2(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("attack/xss/xss");
		
		String id = request.getParameter("script");
		BoardListVO result = boardService.getBoardById(id);
		// XSS Case 2. 결과에 대해 HTML Encoding 처리하기
		
		String content = result.getList().get(0).getContent();
		
		XssFilter xssFilter = XssFilter.getInstance("/lucy-xss-superset.xml");
		content = xssFilter.doFilter(content);
		result.getList().get(0).setContent(content);
		
		view.addObject("result", result.getList().get(0).getContent());
		view.addObject("requestedString2", id);
		
		return view;
	}
	
	@RequestMapping("/attack/xss/attack3")
	public void attack3(HttpServletRequest request, HttpServletResponse response) {
		String requestedString = request.getParameter("script");
		// XSS Case 3. 요청 값에 대해 HTML Encoding 처리하기
		
		XssFilter xssFilter = XssFilter.getInstance("/lucy-xss-superset.xml");
		requestedString = xssFilter.doFilter(requestedString);
		
		SendMessage.send(response, "{ \"result\" : \""+requestedString+"\", \"requestedString3\" : \""+requestedString+"\"}");
	}
	
}
