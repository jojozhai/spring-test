/**
 * 
 */
package com.lesson.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lesson.BaseTest;
import com.lesson.dto.BookInfo;
import com.lesson.repository.BookRepository;
import com.lesson.service.BookService;

/**
 * @author zhailiang
 *
 */
public class BookServiceTest extends BaseTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void whenCreateSuccess() {
		
		long count = bookRepository.count();
		
		BookInfo info = new BookInfo();
		info.setName("test book 1");
		
		info = bookService.create(info);
		
		Assert.assertEquals(count + 1, bookRepository.count());
		Assert.assertEquals("test book 1", bookRepository.findOne(info.getId()).getName());
		
	}
	
	@Test
	public void whenUpdateSuccess() {
		
		BookInfo info = new BookInfo();
		info.setId(1L);
		info.setName("test book 2");
		
		info = bookService.update(info);
		
		Assert.assertEquals("test book 2", bookRepository.findOne(info.getId()).getName());
		
	}

}
