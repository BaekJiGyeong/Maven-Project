public class Main {

	public static void main(String[] args) {
		LoginStore loginStore = LoginStore.getInstance();
		loginStore.add("ABC");
		loginStore.add("ABCD");
		loginStore.add("ABCDE");
		
		LoginStore loginStore2 = LoginStore.getInstance();
		System.out.println(loginStore2.get(0));
		System.out.println(loginStore2.get(1));
		System.out.println(loginStore2.get(2));
	}
	
}
