/**
 * 
 */
package com.lesson.spring.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author zhailiang
 *
 */
public class PriceDemo {

	private List<Shop> shops = Arrays.asList(new Shop("shop1"), new Shop("shop2"), new Shop("shop3"), new Shop("shop4"),
			new Shop("shop5"), new Shop("shop6"), new Shop("shop7"), new Shop("shop8"), new Shop("shop9"),
			new Shop("shop10")
			, new Shop("shop11")
			, new Shop("shop12")
			, new Shop("shop13")
			, new Shop("shop14")
			, new Shop("shop15")
			, new Shop("shop16")
			, new Shop("shop17"));

//	public List<String> findPrices(String product) {
//		 return shops.stream().map(shop -> String.format("%s price is%.2f", shop.getName(), shop.getPrice(product)))
//		 .collect(Collectors.toList());
//		}
	
//	public List<String> findPrices(String product) {
//	 return shops.stream().parallel().map(shop -> String.format("%s price is%.2f", shop.getName(), shop.getPrice(product)))
//	 .collect(Collectors.toList());
//	}

	public void findPrices(String product) {
		
		long start = System.currentTimeMillis();
		
		Executor executor = Executors.newFixedThreadPool(100);
		
		CompletableFuture<?>[] priceFuture = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor).thenCombine( 
						CompletableFuture.supplyAsync(() -> ExchangeDemo.getRate("USD", "CNY"), executor), 
						(quote, rate) -> new Quote(quote.getShop(), quote.getPrice() * rate, quote.getDiscount())))
				.map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> DiscountDemo.applyDiscount(quote), executor)))
				
//				.map(future -> future.thenAccept(content ->  
//				System.out.println(content + " (done in " + (System.currentTimeMillis() - start )+" msecs)")))
		.toArray(size -> new CompletableFuture[size]);
//				.collect(Collectors.toList());
		 
		CompletableFuture.anyOf(priceFuture).thenAccept((obj) -> System.out.println("fastest done" + obj));
		
//		return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
		
	}

	public static void main(String[] args) {
//		System.out.println(Runtime.getRuntime().availableProcessors());
		PriceDemo priceDemo = new PriceDemo();
		long start = System.currentTimeMillis();
//		System.out.println(priceDemo.findPrices("iPhone7"));
		priceDemo.findPrices("iPhone7");
		System.out.println("服务耗时:" + (System.currentTimeMillis() - start));
	}

}
