/**
 * 
 */
package com.lesson.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lesson.aspect.ServiceLog;
import com.lesson.domain.Book;
import com.lesson.dto.BookCondition;
import com.lesson.dto.BookInfo;
import com.lesson.lock.GlobalLock;
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
//@Transactional
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	@Override
	@Cacheable("books")
	public BookInfo getInfo(Long id) {
		Book book = bookRepository.findOne(id);
		BookInfo info = new BookInfo();
		BeanUtils.copyProperties(book, info);
		
		return info;
	}

	@Override
	@ServiceLog
	@Transactional
	@CacheEvict(cacheNames = "books", allEntries = true)
	public Page<BookInfo> query(BookCondition condition, Pageable pageable) {
		
		Page<Book> pageData = bookRepository.findAll(new BookSpec(condition), pageable);
		return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<Book, BookInfo>() {
			@Override
			protected void doConvert(Book domain, BookInfo info) throws Exception {
				info.setContent("xxx");
			}
		});
	}

	private void updateUserBalance() {
		// TODO Auto-generated method stub
		
	}

	private void updateStock(BookInfo bookInfo) {
		// TODO Auto-generated method stub
		
	}

	private void createOrder(BookInfo bookInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BookInfo create(BookInfo info) {
		
		if(StringUtils.equals(info.getName(), "b")){
			throw new RuntimeException("测试事务");
		}
		
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

	@Override
	@Scheduled(cron = "0/3 * * * * *")
	@GlobalLock(path = "/book/task")
	public void task() throws Exception {
		Map<String, JobParameter> param = new HashMap<>();
		param.put("startTime", new JobParameter(new Date()));
		jobLauncher.run(job, new JobParameters(param));	
	}

}
