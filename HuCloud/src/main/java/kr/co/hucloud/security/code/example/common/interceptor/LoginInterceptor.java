package kr.co.hucloud.security.code.example.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.hucloud.security.code.example.common.Session;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter  {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		boolean wasLogin = request.getSession().getAttribute(Session.MEMBER) != null;

		if (!wasLogin) {
			response.sendRedirect("http://localhost:8080/HuCloud");
		}

		return wasLogin;
	}
	
}
