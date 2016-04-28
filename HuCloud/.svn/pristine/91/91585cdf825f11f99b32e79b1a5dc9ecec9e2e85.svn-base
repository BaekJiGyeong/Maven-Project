package kr.co.hucloud.utilities.session;

import javax.servlet.http.HttpSession;

public class SessionUtils {

	
	public static <T> void put(HttpSession session, String key, T value) {
		session.setAttribute(key, value);
	}
	
	public static <T> T get(HttpSession session, String key) {
		return (T) session.getAttribute(key);
	}
	
}
