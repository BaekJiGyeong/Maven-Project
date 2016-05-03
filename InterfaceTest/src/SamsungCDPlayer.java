
public class SamsungCDPlayer implements CDPlayer{

	@Override
	public void play() {
		System.out.println("위이이잉~");
		System.out.println("음악을 재생하고 있습니다.");
		
	}

	@Override
	public void stop() {
		System.out.println("드르르륵..");
		System.out.println("음악을 일시정지 했습니다.");
	}

	@Override
	public void next() {
		System.out.println("드르르륵...위이이잉~");
		System.out.println("다음 트랙을 찾아 재생합니다..");
	}

	@Override
	public void prev() {
		System.out.println("드르르륵.. 위이이잉....");
		System.out.println("이전트랙을 찾아 재생합니다.");
	}
	
	public void supple() {
		System.out.println("그런 기능은 없습니다.");
	}

}
