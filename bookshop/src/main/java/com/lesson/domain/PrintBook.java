/**
 * 
 */
package com.lesson.domain;

import java.util.Date;

import javax.persistence.Entity;

/**
 * @author zhailiang
 *
 */
@Entity
public class PrintBook extends Book {
	
	private Date printDate;

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}
	
}
