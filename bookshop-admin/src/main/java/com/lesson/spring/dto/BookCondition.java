/**
 * 
 */
package com.lesson.spring.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhailiang
 *
 */
public class BookCondition {

	private String name;
	
	@ApiModelProperty("门类ID")
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
