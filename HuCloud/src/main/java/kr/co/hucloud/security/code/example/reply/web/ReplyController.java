package kr.co.hucloud.security.code.example.reply.web;

import javax.servlet.http.HttpSession;

import kr.co.hucloud.security.code.example.common.Session;
import kr.co.hucloud.security.code.example.member.vo.MemberVO;
import kr.co.hucloud.security.code.example.reply.service.ReplyService;
import kr.co.hucloud.security.code.example.reply.vo.ReplyVO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReplyController {

	private ReplyService replyService;
	
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@RequestMapping(value="/reply/write", method=RequestMethod.POST)
	public ModelAndView wirteReply(ReplyVO reply, HttpSession session) {
		
		MemberVO member = (MemberVO) session.getAttribute(Session.MEMBER);
		reply.setUserId(member.getId());
		
		replyService.insertReply(reply);
		
		return new ModelAndView("redirect:/board/article/" + reply.getBoardId());
	}
	
	@RequestMapping(value="/reply/recommend/{boardId}/{id}")
	public ModelAndView wirteReply(@PathVariable String boardId, @PathVariable String id) {
		replyService.updateRecommend(boardId, id);
		return new ModelAndView("redirect:/board/article/" + boardId);
	}
	
	
}
