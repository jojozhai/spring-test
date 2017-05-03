/**
 * 
 */
package com.lesson.domain;

import javax.persistence.Entity;

/**
 * @author zhailiang
 *
 */
@Entity
public class Ebook extends Book {
	
	private String format;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
}
