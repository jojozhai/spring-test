/**
 * 
 */
package com.lesson.spring.future;

import java.util.Random;

/**
 * @author zhailiang
 *
 */
public class ExchangeDemo {
	
	public static double getRate(String source, String target) { 
		delay();
		return 10;
	}

	public static void delay() {
		int delay = 500 + new Random().nextInt(2000);
		try {
			Thread.sleep(delay);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
