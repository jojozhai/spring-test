/**
 * 
 */
package com.lesson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lesson.support.BookShopRepositoryImpl;

/**
 * @author zhailiang
 *
 */
@SpringBootApplication
@ImportResource("classpath:provider.xml")
@EnableJpaRepositories(repositoryBaseClass = BookShopRepositoryImpl.class)
public class BookShopApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BookShopApplication.class);
//		application.setAdditionalProfiles("pro");
		application.run(args);
	}

}
