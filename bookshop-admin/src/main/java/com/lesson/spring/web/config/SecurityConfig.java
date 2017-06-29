/**
 * 
 */
package com.lesson.spring.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author zhailiang
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	MyUserDetaisService myUserDetaisService;
	
	@Autowired
	private AuthenticationSuccessHandler bookShopAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler bookShopAuthenticationFailureHandler;
	
//	@Autowired
//	private DataSource dataSource;
	
//	@Bean
//	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
//	        OAuth2ProtectedResourceDetails details) {
//	    return new OAuth2RestTemplate(details, oauth2ClientContext);
//	}
	
//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
////		tokenRepository.setCreateTableOnStartup(true);
//		tokenRepository.setDataSource(dataSource);
//		return tokenRepository;
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetaisService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		SpringSocialConfigurer socialConfigurer = new SpringSocialConfigurer();
		socialConfigurer.signupUrl("/regist.html");
		
		http.httpBasic()
				.and()
			.formLogin()
				.loginPage("/login.html")
				.loginProcessingUrl("/auth")
				.usernameParameter("user")
				.passwordParameter("pass")
				.successHandler(bookShopAuthenticationSuccessHandler)
				.failureHandler(bookShopAuthenticationFailureHandler)
				.and()
//			.rememberMe()
//				.tokenRepository(persistentTokenRepository())
//				.tokenValiditySeconds(60)
//				.and()
//			.sessionManagement()
//				.sessionFixation().changeSessionId()
//				.invalidSessionUrl("/session.html")
//				.maximumSessions(1)
//				.maxSessionsPreventsLogin(true)
//				.and()
//				.and()
			.csrf().disable()
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//				.and()
				.authorizeRequests()
				.antMatchers("/webjars/**", "/login", "/registUser","/book", "/regist.html", "/login.html", "/auth/**", "/session.html").permitAll()
				.anyRequest().authenticated()
				.and().apply(socialConfigurer);
				//.access("@bookSecurity.check(authentication,request)");
		
	}

}
