/*
 * 项目名称：pzx-framework-data-redis
 * 类名称: LockConfig
 * 创建时间: 2017年2月21日 下午4:34:02
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2017 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.lesson.lock;

import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author zhailiang@pz365.com
 */
@Configuration
public class LockConfig {
    
    @Autowired
    private Map<String, RedissonConfigBuilder> redissonConfigBuilders;
    
    @Autowired
    private RedisProperties redisProperties;
    
    @Bean
    public RedissonClient redissonClient() {
        RedissonConfigBuilder redissonConfigBuilder;
        if(redisProperties.getCluster() == null) {
            redissonConfigBuilder = redissonConfigBuilders.get("singleServerRedissonConfigBuilder");
        }else{
            redissonConfigBuilder = redissonConfigBuilders.get("clusterServerRedissonConfigBuilder");
        }
        return Redisson.create(redissonConfigBuilder.build(redisProperties));
    }

}
