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

import com.lesson.aspect.ServiceLog;
import com.lesson.domain.Book;
import com.lesson.dto.BookCondition;
import com.lesson.dto.BookInfo;
import com.lesson.repository.BookRepository;
import com.lesson.repository.spec.BookSpec;
import com.lesson.service.BookService;
import com.lesson.support.AbstractDomain2InfoConverter;
import com.lesson.support.QueryResultConverter;

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
	@ServiceLog
	public Page<BookInfo> query(BookCondition condition, Pageable pageable) {
		Page<Book> pageData = bookRepository.findAll(new BookSpec(condition), pageable);
		return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<Book, BookInfo>() {
			@Override
			protected void doConvert(Book domain, BookInfo info) throws Exception {
				info.setContent("xxx");
			}
		});
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
