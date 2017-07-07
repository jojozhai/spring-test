/*
 * 项目名称：pzx-framework-dubbo
 * 类名称: DistributedLockAspect
 * 创建时间: 2016年12月23日 下午5:17:03
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.lesson.lock;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Profile;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 *
 *
 * @author zhailiang@pz365.com
 */
@Aspect
@Component
@Profile("!junit")
public class GlobalLockAspect {

    @Autowired
    private RedissonClient redisson;

    @Autowired
    private CacheManager cacheManager;

    private static final String pathSeparator = ":";

    private static final String lockPathPrefix = "lock";

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalLockAspect.class);

    /**
     * 使用分布式锁来保证相同的lockPath被并发请求时，在分布式环境中只会被一个JVM处理。
     * 
     * 当多个线程试图获取同一个锁时，未获取到锁的线程会抛出DistributedLockConflictException异常。
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     * @author zhailiang
     * @since 2016年12月24日
     */
    @Around("@annotation(com.lesson.lock.GlobalLock)")
    public Object invokeMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        Method method = getMethod(joinPoint);
        GlobalLock distributedLock = method.getAnnotation(GlobalLock.class);

        String lockPath = parseLockPath(joinPoint, method, distributedLock);

        RLock lock = getLockFromCache(lockPath);

        if (lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime() * 1000, TimeUnit.MILLISECONDS)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("get lock on path:" + lockPath);
            }

            try {
                return joinPoint.proceed();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("release lock on path:" + lockPath);
                    }
                }
            }
        } else {
            throw new GlobalLockConflictException(lockPath);
        }

    }

    /**
     * 解析锁的路径
     * 
     * @param joinPoint
     * @param method
     * @param distributedLock
     * @return
     * @author zhailiang
     * @since 2017年2月22日
     */
    private String parseLockPath(ProceedingJoinPoint joinPoint, Method method, GlobalLock distributedLock) {

        String lockPath = distributedLock.path();

        if (StringUtils.isBlank(lockPath)) {
            lockPath = joinPoint.getTarget().getClass().getSimpleName() + pathSeparator + method.getName();
        }

        if (StringUtils.isNotBlank(distributedLock.key())) {
            String key = parseKey(distributedLock.key(), method, joinPoint.getArgs());
            lockPath = lockPath + pathSeparator + key;
        }

        lockPath = lockPathPrefix + pathSeparator + lockPath;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("lock path is:" + lockPath);
        }
        return lockPath;
    }

    /**
     * 可重入锁可以被重复使用。所以使用缓存来保存锁。
     * 
     * @return
     * @author zhailiang
     * @since 2016年12月24日
     */
    private RLock getLockFromCache(String lockPath) {
//        Cache cache = cacheManager.getCache("Lock");
        RLock lock = null;
//        if (cache != null) {
//            lock = cache.get(lockPath, RLock.class);
//        }
//        if (lock == null) {
            lock = redisson.getLock(lockPath);
//
//            if (cache != null) {
//                cache.put(lockPath, lock);
//            }
//        }
        return lock;
    }

    /**
     * 获取锁的路径 lockPath 定义在注解上，支持SPEL表达式
     *
     * @return
     */
    private String parseKey(String lockPath, Method method, Object[] args) {

        Assert.hasText(lockPath, "锁路径(lockPath)不能为空");

        // 获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        // 使用SPEL进行key的解析
        SpelExpressionParser parser = new SpelExpressionParser();
        // SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        // 把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(lockPath).getValue(context, String.class);
    }

    /**
     * 获取被拦截方法对象
     * <p>
     * MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象 而缓存的注解在实现类的方法上
     * 所以应该使用反射获取当前对象的方法对象
     */
    private Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass().getMethod(signature.getName(), method.getParameterTypes());
            } catch (SecurityException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.error("lockPoint getMethod", e);
                }
            } catch (NoSuchMethodException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.error("lockPoint getMethod", e);
                }
            }
        }
        return method;
    }

}
