/**
 * 
 */
package com.lesson.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhailiang
 *
 */
public class BookInfo implements Serializable {
	
	public interface BookListView {};
	public interface BookDetailView extends BookListView {};
	
	public BookInfo() {}
	
	public BookInfo(String name) {
		this.name = name;
	}
	
	private Long id;
	
	private String name;
	
	private String content;
	
	private Date publishDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
}
