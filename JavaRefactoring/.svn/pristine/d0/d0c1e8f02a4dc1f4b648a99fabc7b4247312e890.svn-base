package kr.co.hucloud.refactoring.chapter.solid.ocp;

import java.util.ArrayList;
import java.util.List;

public class AreaCalculator {

	public double getArea(Rectangle rec) {
		return rec.getWidth() * rec.getHeight();
	}
	
	public double getArea(Triangle triangle) {
		return triangle.getBase() * triangle.getHeight() / 2;
	}
	
	public double getArea(Circle circle) {
		return circle.getRadius() * circle.getRadius() * Math.PI;
	}

	public double getTotalArea(List<Object> figures) {
		double totalArea = 0.0;
		
		for (Object figure : figures ) {
			if(figure instanceof Rectangle) {
				totalArea += getArea((Rectangle) figure);
			}
			else if(figure instanceof Triangle) {
				totalArea += getArea((Triangle) figure);
			}
			else if(figure instanceof Circle) {
				totalArea += getArea((Circle) figure);
			}
		}
		
		return totalArea;
	}
	
	public static void main(String[] argv) {
		AreaCalculator c = new AreaCalculator();
		
		Rectangle rec = new Rectangle(2, 3);
		Triangle tri = new Triangle(2, 3);
		Circle cir = new Circle(3);
		System.out.println(String.format("사각형의 넓이는 %f", c.getArea(rec)));
		System.out.println(String.format("삼각형의 넓이는 %f", c.getArea(tri)));
		System.out.println(String.format("원의 넓이는 %f", c.getArea(cir)));
		
		List<Object> figures = new ArrayList<Object>();
		figures.add(rec);
		figures.add(tri);
		figures.add(cir);
		System.out.println(String.format("총합은 %f", c.getTotalArea(figures)));
	}
	
}

