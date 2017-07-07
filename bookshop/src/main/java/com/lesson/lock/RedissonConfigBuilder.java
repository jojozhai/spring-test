/*
 * 项目名称：pzx-framework-data-redis
 * 类名称: RedissonConfigFactory
 * 创建时间: 2017年2月21日 下午4:40:11
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2017 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.lesson.lock;

import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

/**
 *
 *
 * @author zhailiang@pz365.com
 */
public interface RedissonConfigBuilder {
    
    Config build(RedisProperties redisProperties);

}
