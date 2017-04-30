/**
 * 
 */
package com.roncoo.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.roncoo.BaseTest;
import com.roncoo.domain.Book;

/**
 * @author zhailiang
 *
 */
public class RepositoryTest extends BaseTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void test1(){
		bookRepository.save(new Book());
	}

}
