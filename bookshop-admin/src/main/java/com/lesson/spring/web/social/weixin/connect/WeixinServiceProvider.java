/**
 * 
 */
package com.lesson.spring.web.social.weixin.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;

import com.lesson.spring.web.social.weixin.api.Weixin;
import com.lesson.spring.web.social.weixin.api.impl.WeixinTemplate;
import com.lesson.spring.web.social.weixin.config.WeixinOAuth2Template;

/**
 * @author zhailiang
 *
 */
public class WeixinServiceProvider extends AbstractOAuth2ServiceProvider<Weixin> {
	
	/**
	 * 微信获取授权码的url
	 */
	private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
	/**
	 * 微信获取accessToken的url
	 */
	private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

	/**
	 * @param appId
	 * @param appSecret
	 */
	public WeixinServiceProvider(String appId, String appSecret) {
		super(getOAuth2Template(appId, appSecret));
	}

	/**
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	private static OAuth2Operations getOAuth2Template(String appId, String appSecret) {
		WeixinOAuth2Template oAuth2Template = new WeixinOAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN);
		oAuth2Template.setUseParametersForClientAuthentication(true);
		return oAuth2Template;
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.oauth2.AbstractOAuth2ServiceProvider#getApi(java.lang.String)
	 */
	@Override
	public Weixin getApi(String accessToken) {
		return new WeixinTemplate(accessToken);
	}

}

