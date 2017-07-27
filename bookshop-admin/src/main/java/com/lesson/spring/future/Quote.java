/**
 * 
 */
package com.lesson.spring.future;

import org.apache.commons.lang.StringUtils;

/**
 * @author zhailiang
 *
 */
public class Quote {
	
	private final String shop;
	private final double price;
	private final Discount discount;
	
	public Quote(String shop, double price, Discount discount) {
		this.shop = shop;
		this.price = price;
		this.discount = discount;
	}
	
	public static Quote parse(String content) {
		String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(content, ":");
		return new Quote(items[0], Double.parseDouble(items[1]), Discount.valueOf(items[2]));
	}

	public String getShop() {
		return shop;
	}

	public double getPrice() {
		return price;
	}

	public Discount getDiscount() {
		return discount;
	}
	
}
