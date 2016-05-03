package kr.co.hucloud.refactoring.chapter.solid.isp;

public class Pigeon implements Bird {
	@Override
	public void fly() {
		System.out.println("fly!");
	}

	@Override
	public void sing() {
		System.out.println("coo coo coo coo");
	}

	@Override
	public void eat() {
		System.out.println("eat eat");
	}
}

