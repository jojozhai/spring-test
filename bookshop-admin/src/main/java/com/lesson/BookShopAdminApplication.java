/**
 * 
 */
package com.lesson;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.lesson.spring.web.config.TestProperties;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhailiang
 *
 */
@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EnableScheduling
@EnableConfigurationProperties(value = TestProperties.class)
@ImportResource("classpath:consumer.xml")
//@EnableJdbcHttpSession
public class BookShopAdminApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BookShopAdminApplication.class);
		application.setAdditionalProfiles("prod", "a");
		application.run(args);
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
