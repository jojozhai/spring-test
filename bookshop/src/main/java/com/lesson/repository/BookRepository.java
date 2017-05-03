/**
 * 
 */
package com.lesson.repository;

import org.springframework.data.jpa.repository.EntityGraph;

import com.lesson.domain.Book;
import com.lesson.support.BookShopRepository;

/**
 * @author zhailiang
 *
 */
public interface BookRepository extends BookShopRepository<Book> {
	
	@EntityGraph("Book.fetch.category.and.author")
	Book findByName(String bookname);
	
}
