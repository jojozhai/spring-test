/**
 * 
 */
package com.lesson.spring.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.lesson.spring.web.social.weixin.api.Weixin;
import com.lesson.spring.web.social.weixin.api.WeixinUserProfile;

/**
 * @author zhailiang
 *
 */
@RestController
public class UserController {
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	@RequestMapping("/getRegistUserInfo")
	public Map<String, String> getRegistUserInfo(WebRequest request) {
		Map<String, String> map = new HashMap<>();
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
		if(connection != null) {
			String providerId = connection.getKey().getProviderId();
			Object api = connection.getApi();
			if(api instanceof Weixin) {
				WeixinUserProfile service = ((Weixin)api).getUserProfile(connection.getKey().getProviderUserId());
				map.put("nickname", service.getNickname());
				map.put("headimg", service.getHeadimgurl());
				map.put("provider", providerId);
			}
		}
		return map;
	}
	
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
