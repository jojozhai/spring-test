/**
 * 
 */
package com.lesson.spring.web.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component
public class MyUserDetaisService implements UserDetailsService {

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(StringUtils.equals(username, "zhangsan")){
			String password = new BCryptPasswordEncoder().encode("111111");
			return new User("zhangsan", password, AuthorityUtils.createAuthorityList("admin","xxxx"));
		}
		return null;
	}

}
