/*
 * 项目名称：pzx-framework-dubbo
 * 类名称: DistributedLock
 * 创建时间: 2016年12月23日 下午5:02:14
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.lesson.lock;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *
 * @author zhailiang@pz365.com
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GlobalLock {
	/**
	 * 锁的路径。一般用来描述你是在为什么东西加锁。
	 * 
	 * @return
	 * @author zhailiang
	 * @since 2016年12月23日
	 */
	String path() default "";

	/**
	 * 锁的key,一个Spring表达式。锁的路径和锁的key合起来作为整个锁的标识。
	 * 
	 * @return
	 * @author zhailiang
	 * @since 2016年12月23日
	 */
	String key() default "";

	/**
	 * 
	 * 获取锁的等待时间，单位是毫秒，默认为0，如果获取不到锁就立即抛出异常。注意，这里是获取锁的等待时间，也就是说，如果两个并发线程试图获取同一个锁，
	 * 等待时间是50毫秒的话，如果获取到锁的线程在50毫秒内处理完毕并释放锁的话，另一个线程是可以拿到锁的，所以，服务的幂等性还需要服务自身来保证。
	 * 由于加了@DistributedLock的方法在没拿到锁时至少会等待waitTime参数指定的时间，所以，waitTime应该尽可能小，
	 * 以避免长时间的等待。由于拿不到锁的线程会抛出DistributedLockConflictException，所以，
	 * 如果不希望因为全局锁导致方法失败，waitTime应该有一定值，以便在持有锁的线程释放锁时当前线程可以拿到锁并继续处理。反之，
	 * 如果你希望拿不到锁时立即抛出异常，只要将waitTime设为0即可。
	 *
	 * @return
	 * @author zhailiang
	 * @since 2016年12月23日
	 */
	long waitTime() default 0;

	/**
	 * 锁的租约时间，单位是秒，默认120秒。当前线程在获取到锁以后，在租约时间到期以后，会自动释放锁。如果在租约时间到期之前，方法执行完毕了，也会释放锁。
	 * 
	 * @return
	 * @author zhailiang
	 * @since 2017年2月21日
	 */
	long leaseTime() default 120;

}