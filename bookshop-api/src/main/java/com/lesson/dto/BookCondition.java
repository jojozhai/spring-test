/**
 * 
 */
package com.lesson.dto;

import java.io.Serializable;

/**
 * @author zhailiang
 *
 */
public class BookCondition implements Serializable {

	private String name;
	
	private Long categoryId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
}
