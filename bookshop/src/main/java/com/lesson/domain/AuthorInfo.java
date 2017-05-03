/**
 * 
 */
package com.lesson.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author zhailiang
 *
 */
@Entity
public class AuthorInfo extends DomainImpl {
	
	private String school;
	
	@OneToOne(mappedBy = "info")
	private Author author;

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
	
}
