/*
 * 项目名称：pzx-framework-data-redis
 * 类名称: DefaultRedissonConfigFactory
 * 创建时间: 2017年2月21日 下午4:45:40
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
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author zhailiang@pz365.com
 */
@Component("singleServerRedissonConfigBuilder")
public class SingleServerRedissonConfigBuilder implements RedissonConfigBuilder {

    @Override
    public Config build(RedisProperties redisProperties) {
        Config config = new Config();
        config.useSingleServer().setAddress(redisProperties.getHost()+":"+redisProperties.getPort());
        return config;
    }

}
