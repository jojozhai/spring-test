/**
 * 
 */
package com.lesson.spring.web.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zhailiang
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin()
			.and()
			.authorizeRequests()
				.antMatchers("/book").permitAll()
				.anyRequest().authenticated();
		
	}

}
