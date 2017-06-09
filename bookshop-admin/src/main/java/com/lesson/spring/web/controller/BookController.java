/**
 * 
 */
package com.lesson.spring.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.fasterxml.jackson.annotation.JsonView;
import com.lesson.spring.dto.BookCondition;
import com.lesson.spring.dto.BookInfo;
import com.lesson.spring.dto.BookInfo.BookDetailView;
import com.lesson.spring.dto.BookInfo.BookListView;
import com.lesson.spring.service.BookService;

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
	
	@GetMapping
	@JsonView(BookListView.class)
	@ApiOperation("查询图书信息")
	public List<BookInfo> query(BookCondition condition, @PageableDefault(size = 10) Pageable pageable) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		if(authentication != null) {
			System.out.println(authentication.getPrincipal());
		}
		
//		System.out.println(pageable.getPageNumber());
//		System.out.println(pageable.getPageSize());
//		System.out.println(pageable.getSort());

		List<BookInfo> books = new ArrayList<BookInfo>();
		books.add(new BookInfo());
		books.add(new BookInfo());
		books.add(new BookInfo());

		return books;
	}

	@GetMapping("/{id}")
	@JsonView(BookDetailView.class)
	@ApiOperation("获取图书详细信息")
	public BookInfo getInfo(@ApiParam("图书ID") @PathVariable Long id) throws Exception {
		
		bookService.getInfo(id);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(authentication);
//		if(authentication != null) {
//			System.out.println(authentication.getPrincipal());
//		}
		
//		System.out.println(id);
		BookInfo bookInfo = new BookInfo();
		bookInfo.setName("战争与和平");
		bookInfo.setPublishDate(new Date());
		return bookInfo;
	}
	
	@PostMapping
	public BookInfo create(@Valid @RequestBody BookInfo info, BindingResult result) {
		if(result.hasErrors()) {
			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		System.out.println("id is "+info.getId());
		System.out.println("name is "+info.getName());
		System.out.println("content is "+info.getContent());
		System.out.println("publishDate is "+info.getPublishDate());
		info.setId(1L);
		return info;
	}
	
	@PutMapping("/{id}")
	public BookInfo update(@Valid @RequestBody BookInfo info, BindingResult result) {
		if(result.hasErrors()) {
			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		System.out.println("id is "+info.getId());
		System.out.println("name is "+info.getName());
		System.out.println("content is "+info.getContent());
		System.out.println("publishDate is "+info.getPublishDate());
		return info;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		System.out.println(id);
	}

}
