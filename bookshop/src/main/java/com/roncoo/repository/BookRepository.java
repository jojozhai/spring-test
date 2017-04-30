/**
 * 
 */
package com.roncoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.roncoo.domain.Book;

/**
 * @author zhailiang
 *
 */
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
	
	Book findByName(String bookname);
	
}
