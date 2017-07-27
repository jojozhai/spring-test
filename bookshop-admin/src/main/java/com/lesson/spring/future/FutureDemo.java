/**
 * 
 */
package com.lesson.spring.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author zhailiang
 *
 */
public class FutureDemo {
	
	public static void main(String[] args) throws Exception {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		System.out.println(System.currentTimeMillis() + " start");
		Future<String> futute = executorService.submit(FutureDemo::doSomeLongOperation);
		System.out.println(System.currentTimeMillis() + " : xxxx");
		String result = futute.get(1, TimeUnit.SECONDS);
		System.out.println(System.currentTimeMillis() + " " + result);
		
	}
	
	public static String doSomeLongOperation() throws InterruptedException {
		Thread.sleep(1000);
		return "123";
	}

}
