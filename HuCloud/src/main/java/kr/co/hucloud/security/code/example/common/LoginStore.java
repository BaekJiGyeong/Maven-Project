package kr.co.hucloud.security.code.example.common;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class LoginStore {
	
	private Map<String, HttpSession> loginSessions;
	
	private static volatile  LoginStore loginStore;
	
	private LoginStore() {
		loginSessions = new HashMap<String, HttpSession>();
		
	}
	
	public static synchronized LoginStore getInstance() {		
		
		if ( loginStore == null ) {
			loginStore = new LoginStore();
		}
	
		return loginStore;
		
	}
	
	public void add(String memberId, HttpSession session) {
		
		loginSessions.put(memberId, session);
	}
	
	public HttpSession get(String memberId) {
		return loginSessions.get(memberId);
	}
	
	public void logout(String memberId) {
		if ( loginSessions.containsKey(memberId) ) {
			loginSessions.get(memberId).invalidate();
			loginSessions.remove(memberId);
		}
	}
}
