
public class Main {
	
	public static void main(String[] args) {
		
		// 1. 기존의 방법
		
		LGCDPlayer lgcdPlayer = new LGCDPlayer();
		lgcdPlayer.play();
		lgcdPlayer.stop();
		lgcdPlayer.prev();
		lgcdPlayer.next();
		
		// 2. IS A 관계를 이용한 방법
		// 관계의 정의
		// sub class is a Super class
		// LGCDPlayer is a CDPlayer
		
		CDPlayer lgPlayer = new LGCDPlayer();
		lgPlayer.play();
		lgPlayer.stop();
		lgPlayer.prev();
		lgPlayer.next();
		lgPlayer.supple();
		
		
		CDPlayer samsungPlayer = new SamsungCDPlayer();
		samsungPlayer.play();
		samsungPlayer.stop();
		samsungPlayer.next();
		samsungPlayer.prev();
		samsungPlayer.supple();
		
		
		
	}

}
