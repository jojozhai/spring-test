/**
 * 
 */
package com.lesson.spring.future;

import java.util.Random;

/**
 * @author zhailiang
 *
 */
public class Shop {
	
	private String name;
	
	public Shop(String name) {
		this.name = name;
	}
	
	Random random = new Random();
	
	public void delay() {
		int delay = 500 + random.nextInt(2000);
		try {
			Thread.sleep(delay);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Quote getPrice(String product) {
		delay();
		double price = random.nextDouble() * 100;
		Discount discount = Discount.values()[random.nextInt(Discount.values().length)];
		return new Quote(getName(), price, discount);
	}
	
//	public Future<Double> getPriceAsync(String product) {
//		return CompletableFuture.supplyAsync(() -> getPrice(product));
//	}
	
	public static void main(String[] args) throws Exception {
		
//		Shop shop = new Shop("bestShop");
//		long start = System.currentTimeMillis();
//		Future<Double> futurePrice = shop.getPriceAsync("some product");
//		System.out.println("调用返回,耗时:"+(System.currentTimeMillis() - start));
//		double price = futurePrice.get();
//		System.out.println("价格返回,耗时:"+(System.currentTimeMillis() - start));
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
