/**
 * 
 */
package com.lesson.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * @author zhailiang
 *
 */
@Entity
public class Category extends DomainImpl{
	
	@Column(length = 10, nullable = false, unique = true)
	private String name;
	
	@Transient
	private String xxxx;

	@OneToMany(mappedBy = "category")
	private List<Book> books;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXxxx() {
		return xxxx;
	}

	public void setXxxx(String xxxx) {
		this.xxxx = xxxx;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
