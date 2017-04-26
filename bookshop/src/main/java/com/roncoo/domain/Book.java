/**
 * 
 */
package com.roncoo.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author zhailiang
 *
 */
@Entity
public class Book extends DomainImpl {
	
	private String name;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy = "book")
	private List<BookAuthor> authors;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<BookAuthor> getAuthors() {
		return authors;
	}

	public void setAuthors(List<BookAuthor> authors) {
		this.authors = authors;
	}
	
}
