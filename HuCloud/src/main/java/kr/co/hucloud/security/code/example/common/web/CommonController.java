package kr.co.hucloud.security.code.example.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

	@RequestMapping("/common/top")
	public String top() {
		return "common/top";
	}
	
	@RequestMapping("/common/loginTop")
	public String loginTop() {
		return "common/loginTop";
	}
	
	@RequestMapping("/common/bottom")
	public String bottom() {
		return "common/bottom";
	}
	
	@RequestMapping("/common/menu")
	public String menu() {
		return "common/menu";
	}
}
