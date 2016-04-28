package kr.co.hucloud.security.code.example.attack.openredirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OpenRedirectController {

	@RequestMapping("/attack/openredirect")
	public String openRedirect(HttpServletResponse response) {
		return "attack/openredirect/openredirect";
	}
	
	@RequestMapping("/attack/openredirect/url")
	public ModelAndView redirect(HttpServletRequest request, HttpServletResponse response) {
		
		//FIXME Paros / WireShark / Internet Explorer 에서 변조 가능.
		// Redirect 값은 모두 상수로 변환하고
		// 서버에서 허용하는 URL값을 가지고 있어야 한다.
		String url = request.getParameter("redirectURL");
		if(StringUtils.isEmpty(url)) {
			url = "";
		}
		return new ModelAndView("redirect:" + url);
	}
	
}
