/**
 * 
 */
package com.lesson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lesson.support.BookShopRepositoryImpl;

/**
 * @author zhailiang
 *
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BookShopRepositoryImpl.class)
public class BookShopApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookShopApplication.class, args);
	}

}
