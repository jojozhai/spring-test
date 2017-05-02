/**
 * 
 */
package com.roncoo.repository;

import org.springframework.data.jpa.repository.EntityGraph;

import com.roncoo.domain.Book;
import com.roncoo.support.BookShopRepository;

/**
 * @author zhailiang
 *
 */
public interface BookRepository extends BookShopRepository<Book> {
	
	@EntityGraph("Book.fetch.category.and.author")
	Book findByName(String bookname);
	
}
