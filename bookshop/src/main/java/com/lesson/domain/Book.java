/**
 * 
 */
package com.lesson.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * @author zhailiang
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedEntityGraph(name = "Book.fetch.category.and.author", attributeNodes = { @NamedAttributeNode("category") })
public class Book extends DomainImpl {

	private String name;

	@ManyToOne
	private Category category;

	@OneToMany(mappedBy = "book")
	private List<BookAuthor> authors;
	
	@Version
	private int version;
	
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
