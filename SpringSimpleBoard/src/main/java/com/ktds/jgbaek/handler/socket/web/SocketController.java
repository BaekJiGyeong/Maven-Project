package com.ktds.jgbaek.handler.socket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SocketController {

	@RequestMapping("/chat")
	public String viewChattingPage(){
		return "chatting/chat";
	}
	   @RequestMapping("/paint")
	   public String viewPaintingPage() {
	      return "chatting/paint";
	   }
	   
}
