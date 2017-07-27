/**
 * 
 */
package com.lesson.spring.web.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component
public class ConfigDemo implements InitializingBean {
	
	@Value("${aaa.bbb.ccc.ddd:hello}")
	private String test;
	
	@Autowired
	private TestProperties testProperties;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
//		Set<String> keys = testProperties.getUsers().keySet();
//		for (String key : keys) {
//			System.out.println(key);
//			System.out.println(testProperties.getUsers().get(key));
//		}
		
		System.out.println(testProperties.getYyy().getH());
	}
	
}
