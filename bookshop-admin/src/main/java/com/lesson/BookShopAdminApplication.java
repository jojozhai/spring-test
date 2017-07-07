/**
 * 
 */
package com.lesson;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhailiang
 *
 */
@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EnableScheduling
//@ImportResource("classpath:consumer.xml")
//@EnableJdbcHttpSession
public class BookShopAdminApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookShopAdminApplication.class, args);
	}

	@Bean
	public CacheManagerCustomizer<RedisCacheManager> customizer() {
		return new CacheManagerCustomizer<RedisCacheManager>() {
			@Override
			public void customize(RedisCacheManager cacheManager) {
				Map<String, Long> map = new HashMap<>();
				map.put("books", 1000L);
				map.put("users", 100L);
				cacheManager.setExpires(map);
				
			}
			
		};
	}
}
