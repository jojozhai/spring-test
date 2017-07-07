/**
 * 
 */
package com.lesson.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lesson.dto.BookCondition;
import com.lesson.dto.BookInfo;

/**
 * @author zhailiang
 *
 */
public interface BookService {

//	@PreAuthorize("hasAuthority('xxxx')")
//	void getInfo(Long id);
	
	BookInfo getInfo(Long id);

	Page<BookInfo> query(BookCondition condition, Pageable pageable);

	BookInfo create(BookInfo info);

	BookInfo update(BookInfo info);

	void delete(Long id);
	
	void task() throws Exception;
}
