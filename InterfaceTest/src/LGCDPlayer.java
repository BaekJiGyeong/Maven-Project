
public class LGCDPlayer implements CDPlayer {

	@Override
	public void play() {
		System.out.println("음악을 재생합니다.");
		
	}

	@Override
	public void stop() {
		System.out.println("음악을 멈춥니다.");
		
	}

	@Override
	public void next() {
		System.out.println("다음 음악을 재생합니다.");
		
	}

	@Override
	public void prev() {
		System.out.println("이전 음악을 재생합니다.");
		
	}
	
	public void supple() {
		System.out.println("다음 곳 부터 임의 재생합니다.");
	}

}
