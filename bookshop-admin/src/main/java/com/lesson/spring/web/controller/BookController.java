/**
 * 
 */
package com.lesson.spring.web.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lesson.dto.BookCondition;
import com.lesson.dto.BookInfo;
import com.lesson.service.BookService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author zhailiang
 *
 */
@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping
//	@JsonView(BookListView.class)
	@ApiOperation("查询图书信息")
	public Page<BookInfo> query(BookCondition condition, Pageable pageable) {
		
		logger.info("这是日志!!");
		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(authentication);
//		if(authentication != null) {
//			System.out.println(authentication.getPrincipal());
//		}
//		
////		System.out.println(pageable.getPageNumber());
////		System.out.println(pageable.getPageSize());
////		System.out.println(pageable.getSort());
//
//		List<BookInfo> books = new ArrayList<BookInfo>();
//		books.add(new BookInfo());
//		books.add(new BookInfo());
//		books.add(new BookInfo());

		return bookService.query(condition, pageable);
	}
 
	@GetMapping("/{id}")
//	@JsonView(BookDetailView.class)
	@ApiOperation("获取图书详细信息")
	public BookInfo getInfo(@ApiParam("图书ID") @PathVariable Long id) throws Exception {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		if(authentication != null) {
			System.out.println(authentication.getPrincipal());
		}
		
		return bookService.getInfo(id);
		
//		System.out.println(id);
//		BookInfo bookInfo = new BookInfo();
//		bookInfo.setName("战争与和平");
//		bookInfo.setPublishDate(new Date());
//		return bookInfo;
	}
	
	@PostMapping
	public BookInfo create(@Valid @RequestBody BookInfo info, BindingResult result) {
//		if(result.hasErrors()) {
//			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
//		}
//		System.out.println("id is "+info.getId());
//		System.out.println("name is "+info.getName());
//		System.out.println("content is "+info.getContent());
//		System.out.println("publishDate is "+info.getPublishDate());
//		info.setId(1L);
		
		return bookService.create(info);
	}
	
	@PutMapping("/{id}")
	public BookInfo update(@Valid @RequestBody BookInfo info, BindingResult result) {
//		if(result.hasErrors()) {
//			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
//		}
//		System.out.println("id is "+info.getId());
//		System.out.println("name is "+info.getName());
//		System.out.println("content is "+info.getContent());
//		System.out.println("publishDate is "+info.getPublishDate());
		
		return bookService.update(info);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		System.out.println(id);
		
		bookService.delete(id);
	}

}
