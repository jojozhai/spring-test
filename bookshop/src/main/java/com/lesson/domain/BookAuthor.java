/**
 * 
 */
package com.lesson.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author zhailiang
 *
 */
@Entity
public class BookAuthor extends DomainImpl {
	
	@ManyToOne
	private Book book;
	
	@ManyToOne
	private Author author;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
}
