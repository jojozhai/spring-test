/**
 * 
 */
package com.lesson.spring.service;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author zhailiang
 *
 */
public interface BookService {

	@PreAuthorize("hasAuthority('xxxx')")
	void getInfo(Long id);

}
