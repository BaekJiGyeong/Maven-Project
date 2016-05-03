package kr.co.hucloud.refactoring.chapter.three;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String name;
	private List<Rental> rentals = new ArrayList<Rental>();
	
	public Customer(String name) {
		this.name = name;
	}
	
	public void addRental(Rental rental){
		this.rentals.add(rental);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String statement() {
		double totalAmount = 0;
		int frequentRentalPoints = 0;
		
		String result = this.getName() + " 고객님의 대여기록\n";
		
		for(Rental rental : rentals) {
			double thisAmount = 0;
			
			// 비디오 종류별 대여료 계산
			switch ( rental.getMovie().getPriceCode() ) {
			case Movie.REGULAR :
				thisAmount += 2;
				if ( rental.getDayRented() > 2 ) {
					thisAmount += (rental.getDayRented() - 2) * 1.5;
				}
				break;
			case Movie.NEW_RELEASE :
				thisAmount += rental.getDayRented() * 3;
				break;
			case Movie.CHILDREN : 
				thisAmount += 1.5;
				if( rental.getDayRented() > 3 ) {
					thisAmount += (rental.getDayRented() - 3) * 1.5;
				}
			}
			
			// 적립 포인트를 1 포인트 증가
			frequentRentalPoints++;
			
			// 최신물을 이틀 이상 대여하면 보너스 포인트 지급
			if ( rental.getMovie().getPriceCode() == Movie.NEW_RELEASE &&
					rental.getDayRented() > 2 ) {
				frequentRentalPoints++;
			}
			
			// 이번에 대여하는 비디오 정보와 대여료를 출력
			result += "\t" + rental.getMovie().getTitle() + "\t" +
						String.valueOf(thisAmount) + "\n";
			
			// 현재까지 누적된 총 대여료
			totalAmount += thisAmount;
		}
		
		result += "누적 대여료 : " + String.valueOf(totalAmount) + "\n";
		result += "적립 포인트 : " + String.valueOf(frequentRentalPoints);
		
		return result;
	}
}
