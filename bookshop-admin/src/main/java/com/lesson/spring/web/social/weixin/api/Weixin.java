/**
 * 
 */
package com.lesson.spring.web.social.weixin.api;

/**
 * @author zhailiang
 *
 */
public interface Weixin {
	
	WeixinUserProfile getUserProfile(String openId);

}
