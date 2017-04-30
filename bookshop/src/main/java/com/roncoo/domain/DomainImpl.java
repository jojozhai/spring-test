/**
 * 
 */
package com.roncoo.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;

/**
 * @author zhailiang
 *
 */
@MappedSuperclass
public class DomainImpl {

	@Id
	@GeneratedValue
	private Long id;
	
	@CreatedDate
	private Date createdTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
