/**
 * 
 */
package com.lesson.spring.web.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;

/**
 * @author zhailiang
 *
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	/* (non-Javadoc)
	 * @see org.springframework.social.config.annotation.SocialConfigurerAdapter#getUsersConnectionRepository(org.springframework.social.connect.ConnectionFactoryLocator)
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
//		repository.setTablePrefix("pzx_");
//		repository.setConnectionSignUp(new AccountConnectionSignUp());
		return repository;
	}
	
	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private UsersConnectionRepository connectionRepository;

	/**
	 * 第三方认证成功以后，如果无法找到对应的本地用户id,
	 * 系统会跳转到注册页面，在跳转前，用这个类把第三方用户信息放入session，跳转后可以用这个类再拿出来，
	 * 也可以在用户创建新用户后绑定新用户和第三方用户。
	 * 
	 * @return
	 */
	@Bean
	public ProviderSignInUtils providerSignInUtils() {
		return new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
	}

}

