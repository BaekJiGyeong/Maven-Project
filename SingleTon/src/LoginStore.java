import java.util.ArrayList;
import java.util.List;

public class LoginStore {
	
	private List<String> loginSessions;
	
	private static volatile  LoginStore loginStore;
	
	private LoginStore() {
		loginSessions = new ArrayList<String>();
		
	}
	
	public static synchronized LoginStore getInstance() {		
		
		if ( loginStore == null ) {
			loginStore = new LoginStore();
		}
	
		return loginStore;
		
	}
	
	public void add(String sessionId) {
		
		loginSessions.add(sessionId);
	}
	
	public String get(int index) {
		return loginSessions.get(index);
	}
	
	
}
