/**
 * 
 */
package com.lesson.spring.async;

import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * @author zhailiang
 *
 */
public class ParallelStreamDemo {

	public static long parallelSum(long n) {
		return LongStream.rangeClosed(0, n).parallel().reduce(0L, Long::sum);
	}

	public static long sequenceSum(long n) {
		return LongStream.rangeClosed(0, n).reduce(0L, Long::sum);
	}

	public static long iterativeSum(long n) {
		long result = 0;
		for (int i = 1; i <= n; i++) {
			result = +i;
		}
		return result;
	}

	public static long test(Function<Long, Long> computer, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.currentTimeMillis();
			System.out.println("result is :"+computer.apply(n));
			long cost = System.currentTimeMillis() - start;
			if (cost < fastest) {
				fastest = cost;
			}
		}
		return fastest;
	}
	
	public static long wrongSum(long n) {
		Counter counter = new Counter();
		LongStream.rangeClosed(0, n).parallel().forEach(counter::add);
		return counter.total;
	}

	public static void main(String[] args) {
		long n = 20_000_000;
//		System.out.println("顺行流:"+test(ParallelStreamDemo::sequenceSum, n));
//		System.out.println("并行流:"+test(ParallelStreamDemo::parallelSum, n));
//		System.out.println("迭代:"+test(ParallelStreamDemo::iterativeSum, n));
		System.out.println("并行流:"+test(ParallelStreamDemo::wrongSum, n));
	}

}
