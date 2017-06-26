/**
 * 
 */
package com.lesson.aspect;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;


/**
 * @author zhailiang
 *
 */
@Component
@Aspect
public class ServiceLogAspect {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(ServiceLogAspect.class);
	
	@Around("@annotation(com.lesson.aspect.ServiceLog)")
	public Object logServiceInvoke(ProceedingJoinPoint pjp) throws Throwable {
		return doLog(pjp);
	}

    protected Object doLog(ProceedingJoinPoint pjp) throws Throwable {
        if(LOGGER.isInfoEnabled()) {
            LOGGER.info("*****调用服务:"+pjp.getSignature().toLongString()+"*****");
            for (Object arg : pjp.getArgs()) {
                printObj(arg, "服务参数:");
            }
            try {
                Object retVal = pjp.proceed();
                printObj(retVal, "返回结果:");
                return retVal;
            } catch (Throwable e) {
                LOGGER.info("抛出异常", e);
                throw e;
            } finally {
                LOGGER.info("*****调用服务结束*****");
            }
        }
        return pjp.proceed();
    }

    /**
     * 记录参数
     * @param arg
     * @param prefix
     * @author zhailiang
     * @since 2016年12月19日
     */
    @SuppressWarnings("rawtypes")
    void printObj(Object arg, String prefix) {
        if(arg != null) {
            if(arg.getClass().isArray()) {
                if(ArrayUtils.isNotEmpty((Object[]) arg)) {
                    Object[] args = (Object[]) arg;
                    for (Object object : args) {
                        printObj(object, prefix);
                    }
                }
            }else if(arg instanceof Collection) {
                if(CollectionUtils.isNotEmpty((Collection) arg)) {
                    Collection collection = (Collection) arg;
                    for (Object object : collection) {
                        printObj(object, prefix);
                    }
                }
            }
            
            if(ClassUtils.isPrimitiveOrWrapper(arg.getClass())){
                LOGGER.info(prefix+arg.toString());
            }else if(arg instanceof String) {
                LOGGER.info(prefix+(String)arg);
            }else{
                LOGGER.info(prefix+ReflectionToStringBuilder.toString(arg));
            }
        }else{
            LOGGER.info(prefix+" null");
        }
    }

}
