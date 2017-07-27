/**
 * 
 */
package com.lesson.spring.future;

/**
 * @author zhailiang
 *
 */
public enum Discount {
	
	NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
	
	private final int percent;
	
	Discount(int percent) {
		this.percent = percent;
	}

	public int getPercent() {
		return percent;
	}
	
	

}
