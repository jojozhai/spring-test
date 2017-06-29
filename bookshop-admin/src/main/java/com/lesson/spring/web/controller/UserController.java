/**
 * 
 */
package com.lesson.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * @author zhailiang
 *
 */
@RestController
public class UserController {
	
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	@RequestMapping("/registUser")
	public String regist(WebRequest request) {
		
		String userId = createUser();
		
		providerSignInUtils.doPostSignUp(userId, request);
		
		return "success";
	}

	private String createUser() {
		return "zhangsan";
	}

}
