/**
 * 
 */
package com.lesson.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lesson.domain.Book;
import com.lesson.dto.BookCondition;
import com.lesson.dto.BookInfo;
import com.lesson.repository.BookRepository;
import com.lesson.service.BookService;

/**
 * @author zhailiang
 *
 */
@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public BookInfo getInfo(Long id) {
		Book book = bookRepository.findOne(id);
		BookInfo info = new BookInfo();
		BeanUtils.copyProperties(book, info);
		return info;
	}

	@Override
	public Page<BookInfo> query(BookCondition condition, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookInfo create(BookInfo info) {
		Book book = new Book();
		book.setName(info.getName());
		bookRepository.save(book);
		info.setId(book.getId());
		return info;
	}

	@Override
	public BookInfo update(BookInfo info) {
		Book book = bookRepository.findOne(info.getId());
		book.setName(info.getName());
		bookRepository.save(book);
		return info;
	}

	@Override
	public void delete(Long id) {
		bookRepository.delete(id);
	}

}
