package kr.co.hucloud.refactoring.chapter.solid.lsp;

public class AnotherWork {

	public boolean checkRect(Rectangle rec) {
		return rec.getArea() == 20;
	}

	public static void main(String[] args) {
		
		AnotherWork work = new AnotherWork();
		
		Rectangle rec = new Square();
		rec.setWidth(4);
		rec.setHeight(5);
		if (!work.checkRect(rec)) {
			throw new RuntimeException();
		}

	}

}

