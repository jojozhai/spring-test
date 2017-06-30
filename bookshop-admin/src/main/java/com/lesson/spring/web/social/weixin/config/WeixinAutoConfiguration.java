/**
 * 
 */
package com.lesson.spring.web.social.weixin.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.boot.autoconfigure.social.SocialWebAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.GenericConnectionStatusView;

import com.lesson.spring.web.social.weixin.api.Weixin;
import com.lesson.spring.web.social.weixin.connect.WeixinConnectionFactory;

/**
 * @author zhailiang
 *
 */
@Configuration
@ConditionalOnClass({ SocialConfigurerAdapter.class, WeixinConnectionFactory.class })
@AutoConfigureBefore(SocialWebAutoConfiguration.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class WeixinAutoConfiguration {

	@Configuration
	@EnableSocial
	@EnableConfigurationProperties(WeixinProperties.class)
	@ConditionalOnWebApplication
	@ConditionalOnProperty(prefix = "pzx.social.weixin", name = "app-id")
	protected static class WeixinConfigurerAdapter extends SocialAutoConfigurerAdapter {

		private final WeixinProperties properties;

		protected WeixinConfigurerAdapter(WeixinProperties properties) {
			this.properties = properties;
		}

		/**
		 * @param repository
		 * @return
		 */
		@Bean
		@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
		public Weixin weixin(ConnectionRepository repository) {
			Connection<Weixin> connection = repository.findPrimaryConnection(Weixin.class);
			return connection != null ? connection.getApi() : null;
		}
		
		/* (non-Javadoc)
		 * @see org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter#createConnectionFactory()
		 */
		@Override
		protected ConnectionFactory<?> createConnectionFactory() {
			return new WeixinConnectionFactory(getProviderId(), this.properties.getAppId(), this.properties.getAppSecret());
		}

		/**
		 * @return
		 */
		@Bean(name = { "connect/weixinConnect", "connect/weixinConnected" })
		@ConditionalOnProperty(prefix = "spring.social", name = "auto-connection-views")
		public GenericConnectionStatusView weixinConnectView() {
			return new GenericConnectionStatusView(getProviderId(), "Weixin");
		}
		
		/**
		 * @return
		 */
		@Bean(name = { "connect/status"})
		@ConditionalOnProperty(prefix = "spring.social", name = "auto-connection-views")
		public JsonConnectView jsonConnectView() {
			return new JsonConnectView();
		}

		/**
		 * @return
		 */
		protected String getProviderId() {
			String providerId = properties.getProviderId();
			if (StringUtils.isBlank(providerId)) {
				providerId = "weixin";
			}
			return providerId;
		}

	}

}
