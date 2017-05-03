/**
 * 
 */
package com.lesson.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

/**
 * @author zhailiang
 *
 */
@MappedSuperclass
public class DomainImpl {

	@Id
	@GeneratedValue(generator = "sequenceGenerator")
	@GenericGenerator(name = "sequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
				@Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "ID_SEQUENCE"),
				@Parameter(name = SequenceStyleGenerator.INITIAL_PARAM, value = "1000"),
				@Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
				@Parameter(name = SequenceStyleGenerator.OPT_PARAM, value = "pooled"),
		}
	)
	private Long id;
	
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
