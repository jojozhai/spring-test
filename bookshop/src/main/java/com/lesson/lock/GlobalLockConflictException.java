/*
 * 项目名称：pzx-framework-data-redis
 * 类名称: DistributedLockException
 * 创建时间: 2017年2月21日 下午5:23:34
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2017 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.lesson.lock;

/**
 * 分布式锁冲突异常
 *
 * @author zhailiang@pz365.com
 */
public class GlobalLockConflictException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -4247606794782331656L;

    public GlobalLockConflictException(String lockPath) {
        super("获取全局锁失败,锁路径为:"+lockPath);
    }

}
