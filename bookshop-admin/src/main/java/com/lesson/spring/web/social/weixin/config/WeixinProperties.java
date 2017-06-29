/**
 * 
 */
package com.lesson.spring.web.social.weixin.config;

import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhailiang
 *
 */
@ConfigurationProperties(prefix = "pzx.social.weixin")
public class WeixinProperties extends SocialProperties {

}
