/**
 * 
 */
package com.lesson.spring.web.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component("bookSecurity")
public class BookSecurity {
	
	public boolean check(Authentication authentication, HttpServletRequest request) {
		
		System.out.println(request.getRequestURI());
		
		Object principal = authentication.getPrincipal();
		if(principal != null && principal instanceof UserDetails) {
			System.out.println(((UserDetails)principal).getAuthorities());
		}
		
		return true;
	}

}
