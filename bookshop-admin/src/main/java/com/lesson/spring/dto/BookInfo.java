/**
 * 
 */
package com.lesson.spring.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhailiang
 *
 */
public class BookInfo {
	
	public interface BookListView {};
	public interface BookDetailView extends BookListView {};
	
	private Long id;
	
	@ApiModelProperty("图书名称")
	private String name;
	
	@NotBlank
	private String content;
	
	private Date publishDate;
	
	@JsonView(BookListView.class)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@JsonView(BookListView.class)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonView(BookDetailView.class)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@JsonView(BookListView.class)
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
}
